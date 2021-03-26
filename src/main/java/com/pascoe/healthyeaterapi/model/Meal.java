package com.pascoe.healthyeaterapi.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private Integer userId;

  @Embedded private List<Food> meal;

  private Integer caloricValue;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate date;
}
