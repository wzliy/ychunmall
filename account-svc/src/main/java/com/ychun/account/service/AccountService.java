package com.ychun.account.service;

import com.ychun.account.model.Users;
import com.ychun.account.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepo accountRepo;

    public boolean usernameIsExist(String username) {
        Users userByName = accountRepo.findUserByUsername(username);
        return userByName != null;
    }
}
