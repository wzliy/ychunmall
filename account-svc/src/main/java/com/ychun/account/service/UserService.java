package com.ychun.account.service;

import com.ychun.account.dto.CreateUserRequest;
import com.ychun.account.dto.UserDto;
import com.ychun.account.dto.UserList;
import com.ychun.account.model.User;
import com.ychun.account.repo.UserRepo;
import com.ychun.common.enums.Sex;
import com.ychun.common.error.ServiceException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public boolean usernameIsExist(String username) {
        User userByName = userRepo.findUserByUsername(username);
        return userByName != null;
    }

    /**
     * 创建用户
     * @param request 用户信息参数
     * @return 用户信息
     */
    public User createUser(CreateUserRequest request) {
        // 查询用户名是否存在
        boolean isExist = usernameIsExist(request.getUsername());
        if (isExist) {
            throw new ServiceException("用户名已经存在");
        }

        User users = new User();
        users.setUsername(request.getUsername());
        users.setPassword(request.getPassword());
        // 设置默认值
        users.setNickname(request.getUsername());
        users.setSex(Sex.Secret.getType());
        return userRepo.save(users);
    }

    /**
     *
     * @param request
     * @return
     */
    public User login(CreateUserRequest request) {
        User users = userRepo.findUsersByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (users == null) {
            throw new ServiceException("用户不存在");
        }
        return users;
    }

    public UserList list(int currentPage, int pageSize) {
        if (pageSize < 0) {
            pageSize = 10;
        }
        if (currentPage <= 0) {
            currentPage = 1;
        }
        Pageable page = PageRequest.of((currentPage - 1), pageSize);
        Page<User> userList = userRepo.findAll(page);
        List<UserDto> userDtoList = userList.getContent().stream().map(this::covertToDto).collect(Collectors.toList());
        return UserList.builder()
                .userList(userDtoList)
                .pageNumber(currentPage)
                .totalPage(userList.getTotalPages())
                .total(userList.getTotalElements())
                .build();

    }

    private UserDto covertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }



}
