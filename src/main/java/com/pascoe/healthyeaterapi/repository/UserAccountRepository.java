package com.pascoe.healthyeaterapi.repository;

import com.pascoe.healthyeaterapi.model.LoginDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<LoginDetails, Integer> {}
