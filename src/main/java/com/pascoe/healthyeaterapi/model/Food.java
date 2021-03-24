package com.pascoe.healthyeaterapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Food {

  private String name;
  private Integer caloricValue;
}

