package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.Meal;
import com.pascoe.healthyeaterapi.service.EdamamService;
import com.pascoe.healthyeaterapi.service.MealsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/meals")
public class MealsController {

  private MealsService mealsService;

  @PostMapping("/{id}")
  public ResponseEntity reportMeals(@PathVariable Integer id, @RequestBody Meal meal) {
    try {
      mealsService.reportMeal(meal);
      return new ResponseEntity(HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity getMeals(@PathVariable Integer id) {
    return mealsService
        .getMeals(id)
        .map(meals -> new ResponseEntity(meals, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }
}
