package com.ychun.account.controller;


import com.ychun.account.dto.CreateUserRequest;
import com.ychun.account.dto.UserList;
import com.ychun.account.model.User;
import com.ychun.account.service.UserService;
import com.ychun.common.api.BaseResponse;
import com.ychun.common.api.ResponseModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 用户模块
 */
@Api(value = "用户登陆注册相关接口", tags = "用户模块")
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @ApiOperation(value = "用户名是否存在")
    @GetMapping("/usernameIsExist")
    public BaseResponse<Boolean> usernameIsExist(@RequestParam @NotBlank String username) {
        return ResponseModel.success(userService.usernameIsExist(username));
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/registry")
    public BaseResponse<User> createUser(@RequestBody @Valid CreateUserRequest request) {
        logger.info("[用户注册]starter");
        return ResponseModel.success(userService.createUser(request));
    }

    // todo 用户登录
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody @Valid CreateUserRequest request) {
        logger.info("[用户登录][{}]starter", request.getUsername());
        return ResponseModel.success(userService.createUser(request));
    }

    @ApiOperation(value = "用户退出登录")
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout(@RequestParam String userId) {
        logger.info("[用户退出登录]" + userId);
        // todo 清空购物车
        // todo 分布式会话中需要清除用户数据
        return ResponseModel.success(true);
    }

    @ApiOperation(value = "查询用户列表")
    @GetMapping("/list")
    public BaseResponse<UserList> listAccount(@RequestParam int currentPage, @RequestParam int pageSize) {
        logger.info("[查询用户列表]");
        UserList userList = userService.list(currentPage, pageSize);
        return ResponseModel.success(userList);
    }




}
