package com.pascoe.healthyeaterapi.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private Integer userId;

  @ElementCollection(targetClass = Food.class)
  @AttributeOverride(name = "name", column = @Column(name = "meal_meal"))
  private List<Food> meal;

  private Integer caloricValue;
  private Date date;
}
