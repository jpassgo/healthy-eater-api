package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

    private UserAccountRepository userAccountRepository;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody UserAccount userAccount) {
        System.out.println(userAccount.toString());
        userAccountRepository.save(userAccount);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

