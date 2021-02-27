package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

  private UserAccountRepository userAccountRepository;

  @PostMapping("/accounts")
  public void createAccount(@RequestBody UserAccount userAccount) {
     userAccountRepository.save(userAccount);
  }
}
