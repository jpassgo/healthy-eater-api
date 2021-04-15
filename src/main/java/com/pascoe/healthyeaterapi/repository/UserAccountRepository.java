package com.pascoe.healthyeaterapi.repository;

import com.pascoe.healthyeaterapi.model.UserAccount;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

  public Optional<UserAccount> findByUserCredentialsUserName(String userName);
}
