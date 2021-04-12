package com.pascoe.healthyeaterapi.repository;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.model.UserCredentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    public Optional<UserAccount> findByUserCredentialsUserName(String userName);
}
