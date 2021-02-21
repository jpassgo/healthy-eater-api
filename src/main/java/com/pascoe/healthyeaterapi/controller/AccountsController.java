package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.LoginDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/accounts")
public class AccountsController {

    @PostMapping
    public void createAccount(@RequestBody LoginDetails userAccount) {

    }
}
