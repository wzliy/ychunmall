package com.ychun.account.controller;


import com.ychun.account.model.UserBO;
import com.ychun.account.model.Users;
import com.ychun.account.model.dto.CreateUserRequest;
import com.ychun.account.service.AccountService;
import com.ychun.common.api.BaseResponse;
import com.ychun.common.api.ResponseModel;
import io.micrometer.core.instrument.Tags;
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
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @ApiOperation(value = "用户名是否存在")
    @GetMapping("/usernameIsExist")
    public BaseResponse<Boolean> usernameIsExist(@RequestParam @NotBlank String username) {
        logger.info("[usernameIsExist]start...");
        return ResponseModel.success(accountService.usernameIsExist(username));
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/registry")
    public BaseResponse<Users> createUser(@RequestBody @Valid CreateUserRequest request) {
        logger.info("[用户注册]starter");
        return ResponseModel.success(accountService.createUser(request));
    }

}
