package com.pascoe.healthyeaterapi.repository;

import com.pascoe.healthyeaterapi.model.Meal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MealsRepository extends CrudRepository<Meal, Integer> {
  Optional<List<Meal>> findAllByUserId(Integer id);
}
