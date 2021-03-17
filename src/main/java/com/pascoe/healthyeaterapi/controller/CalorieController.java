package com.pascoe.healthyeaterapi.controller;

import com.pascoe.healthyeaterapi.client.EdamamClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/calories")
public class CalorieController {

    EdamamClient edamamClient;

    @GetMapping
    public ResponseEntity getCaloriesForFood(@RequestParam(value="food") String food) {
         return ResponseEntity.of(Optional.of(edamamClient.getFoodDetails(
                 "ebd367b9",
                 "8ab028daa0f4b8b8e6c4ddbf32c28757", food)
                 .get("parsed")
                 .get(0)
                 .get("food")
                 .get("nutrients")
                 .get("ENERC_KCAL")));
    }
}
