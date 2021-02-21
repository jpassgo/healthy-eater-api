package com.pascoe.healthyeaterapi.model;

import java.util.List;
import lombok.Data;

@Data
public class Meal {

  private List<Food> meal;
  private Integer caloricValue;
}
