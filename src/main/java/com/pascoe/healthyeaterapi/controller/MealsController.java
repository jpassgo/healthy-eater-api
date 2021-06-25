package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.model.Meal;
import com.pascoe.healthyeaterapi.service.AccountsService;
import com.pascoe.healthyeaterapi.service.MealsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/meals")
public class MealsController {

  private AccountsService accountsService;
  private MealsService mealsService;

  @PostMapping("/{id}")
  public ResponseEntity reportMeals(@PathVariable Integer id, @RequestBody Meal meal) {
    return reportMeal(meal);
  }

  @PostMapping
  public ResponseEntity reportMeals(@RequestBody Meal meal) {
    return reportMeal(meal);
  }

  @GetMapping("/{id}")
  public ResponseEntity getMeals(@PathVariable Integer id) {
    return retrieveMeals(id);
  }

  @RequestMapping(method = RequestMethod.OPTIONS)
  public ResponseEntity options() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("access-control-allow-origin", "*");

    return new ResponseEntity(httpHeaders, HttpStatus.OK);
  }

  private ResponseEntity reportMeal(@RequestBody Meal meal) {
    try {
      mealsService.reportMeal(meal);
      return new ResponseEntity(HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  private ResponseEntity retrieveMeals(@PathVariable Integer id) {
    return mealsService
        .getMeals(id)
        .map(meals -> new ResponseEntity(meals, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
  }
}
