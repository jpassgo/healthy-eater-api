package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.Meal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealsController {

  @PostMapping("/reportMeals")
  public void reportMeals(@RequestBody Meal meal) {}
}
