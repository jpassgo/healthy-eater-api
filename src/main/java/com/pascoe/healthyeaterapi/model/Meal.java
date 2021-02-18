package com.pascoe.healthyeaterapi.model;

import lombok.Data;

import java.util.List;

@Data
public class Meal {

    private List<FoodItem> meal;
    private Integer caloricValue;
}
