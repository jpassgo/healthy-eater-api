package com.pascoe.healthyeaterapi.service;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsService {

  private UserAccountRepository userAccountRepository;

  public UserAccount createAccount(UserAccount userAccount) {
    return userAccountRepository.save(userAccount);
  }

  public Optional<UserAccount> findAccount(Integer id) {
    return userAccountRepository.findById(id);
  }
}
