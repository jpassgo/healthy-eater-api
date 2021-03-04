package com.pascoe.healthyeaterapi.service;

import com.pascoe.healthyeaterapi.model.Meal;
import com.pascoe.healthyeaterapi.repository.MealsRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MealsService {

  private MealsRepository mealsRepository;

  public Meal reportMeal(Meal meal) {
    return mealsRepository.save(meal);
  }

  public List<Meal> getMeals(Integer id) {
    return mealsRepository.findAllByUserId(id).orElseGet(List::of);
  }
}
