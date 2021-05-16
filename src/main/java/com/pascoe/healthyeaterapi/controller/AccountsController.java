package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

  private AccountsService accountsService;

  @PostMapping
  public ResponseEntity createAccount(@RequestBody UserAccount userAccount) {
    try {
      userAccount.getUserCredentials().encryptPassword();
      UserAccount account = accountsService.createAccount(userAccount);
      return new ResponseEntity(account.getId(), HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity retrieveAccount(@PathVariable Integer id) {
    return accountsService
        .findAccount(id)
        .map(userAccount -> new ResponseEntity(userAccount, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }
}
