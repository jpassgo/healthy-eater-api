package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController("/accounts")
public class AccountsController {

  private UserAccountRepository userAccountRepository;

  @PostMapping
  public void createAccount(@RequestBody UserAccount userAccount) {
     userAccountRepository.save(userAccount);
  }
}
