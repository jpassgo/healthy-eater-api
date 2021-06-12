package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.authentication.AuthToken;
import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.model.UserCredentials;
import com.pascoe.healthyeaterapi.service.AccountsService;
import com.pascoe.healthyeaterapi.service.AuthenticationUtils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

  private final AccountsService accountsService;
  private final AuthenticationUtils authenticationUtils;

  @PostMapping
  public ResponseEntity createAccount(@RequestBody UserAccount userAccount) {

    try {
      userAccount.getUserCredentials().encryptPassword();

      if (accountsService.accountExists(userAccount)) {
        accountsService.createAccount(userAccount);
        String jwt = authenticationUtils.generateAuthToken();

        return new ResponseEntity(new AuthToken(jwt), HttpStatus.CREATED);
      } else {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
      }
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.OPTIONS)
  public ResponseEntity options() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("access-control-allow-origin", "*");

    return new ResponseEntity(httpHeaders, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity retrieveAccount(@PathVariable Integer id) {
    return accountsService
        .findAccount(id)
        .map(userAccount -> new ResponseEntity(userAccount, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public ResponseEntity retrieveAccountByCredentials(@RequestBody UserCredentials userCredentials) {
    Optional<UserAccount> optionalUserAccount = accountsService.findAccount(userCredentials);

    if (optionalUserAccount.isPresent()
        && authenticationUtils.doesPasswordMatch(userCredentials, optionalUserAccount)) {
      return new ResponseEntity(optionalUserAccount.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }
}
