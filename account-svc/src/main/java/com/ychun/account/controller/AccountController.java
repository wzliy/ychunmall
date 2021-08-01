package com.ychun.account.controller;


import com.ychun.account.service.AccountService;
import com.ychun.common.api.BaseResponse;
import com.ychun.common.api.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/usernameIsExist")
    public BaseResponse<Boolean> usernameIsExist(@RequestParam String username) {
        return ResponseModel.success(accountService.usernameIsExist(username));
    }
}
