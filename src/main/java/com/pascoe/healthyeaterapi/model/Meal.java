package com.pascoe.healthyeaterapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Integer userId;

  @ElementCollection(targetClass = Food.class)
  @AttributeOverride(name = "name", column = @Column(name = "meal_meal"))
  private List<Food> meal;

  private Integer caloricValue;
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate date;

}
