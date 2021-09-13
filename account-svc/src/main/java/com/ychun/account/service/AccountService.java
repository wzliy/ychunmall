package com.ychun.account.service;

import com.ychun.account.model.UserBO;
import com.ychun.account.model.Users;
import com.ychun.account.model.dto.CreateUserRequest;
import com.ychun.account.repo.AccountRepo;
import com.ychun.common.enums.Sex;
import com.ychun.common.error.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public boolean usernameIsExist(String username) {
        Users userByName = accountRepo.findUserByUsername(username);
        return userByName != null;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public Users createUser(CreateUserRequest request) {
        // 查询用户名是否存在
        boolean isExist = usernameIsExist(request.getUsername());
        if (isExist) {
            throw new ServiceException("用户名已经存在");
        }

        Users users = new Users();
        users.setUsername(request.getUsername());
        users.setPassword(request.getPassword());
        // 设置默认值
        users.setNickname(request.getUsername());
        users.setSex(Sex.Secret.getType());
        return accountRepo.save(users);
    }
}
