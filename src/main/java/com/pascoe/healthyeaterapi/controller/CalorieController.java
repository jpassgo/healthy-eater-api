package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.service.EdamamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/calories")
public class CalorieController {

  EdamamService edamamService;

  @GetMapping
  public ResponseEntity getCaloriesForFood(@RequestParam(value = "food") String food) {
    return ResponseEntity.of(edamamService.getCalories(food));
  }

  @GetMapping("/suggestions")
  public ResponseEntity getSimilarFoodOptions(@RequestParam(value = "food") String food) {
    return ResponseEntity.of(edamamService.getSimilarFoodOptions(food));
  }
}
