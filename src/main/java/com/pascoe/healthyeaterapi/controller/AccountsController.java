package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

    private UserAccountRepository userAccountRepository;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody UserAccount userAccount) {
        System.out.println(userAccount.toString());
        UserAccount newUserAccount = userAccountRepository.save(userAccount);
        System.out.println(newUserAccount.toString());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity retrieveAccount(@PathVariable Integer id) {
        Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(id);

        return optionalUserAccount.map(
                userAccount -> new ResponseEntity(userAccount, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
    }
}

