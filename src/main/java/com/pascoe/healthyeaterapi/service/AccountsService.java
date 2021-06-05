package com.pascoe.healthyeaterapi.service;

import static com.pascoe.healthyeaterapi.service.AccountValidator.isAccountValid;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.model.UserCredentials;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AccountsService {

  private UserAccountRepository userAccountRepository;

  public UserAccount createAccount(UserAccount userAccount) {
    if (isAccountValid(userAccount)) {
      return userAccountRepository.save(userAccount);
    } else {
      throw new IllegalArgumentException();
    }
  }

  public Optional<UserAccount> findAccount(Integer id) {
    return userAccountRepository.findById(id);
  }

  public Optional<UserAccount> findAccount(UserCredentials userCredentials) {
    return userAccountRepository.findByUserCredentialsUserName(userCredentials.getUsername());
  }

  public boolean accountExists(UserAccount userAccount) {
    return !findAccount(userAccount.getUserCredentials()).isPresent();
  }
}
