package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.service.EdamamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/calories")
public class CalorieController {

    EdamamService edamamService;


    @GetMapping
    public ResponseEntity getCaloriesForFood(@RequestParam(value="food") String food) {
         return ResponseEntity.of(edamamService.getCalories(food));
    }
}
