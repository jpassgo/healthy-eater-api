package com.pascoe.healthyeaterapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class FoodConsumptionController {

  @PostMapping
  public void reportMeal() {}
}
