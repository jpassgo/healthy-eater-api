package com.pascoe.healthyeaterapi.repository;

import com.pascoe.healthyeaterapi.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {}
