package com.pascoe.healthyeaterapi.repository;

import com.pascoe.healthyeaterapi.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAccountRepository extends MongoRepository<UserAccount, Long> {}
