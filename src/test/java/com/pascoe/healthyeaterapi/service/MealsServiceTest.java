package com.pascoe.healthyeaterapi.service;

import com.pascoe.healthyeaterapi.model.Meal;
import com.pascoe.healthyeaterapi.repository.MealsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MealsServiceTest {

    @Mock
    private MealsRepository mealsRepository;
    @Mock
    private Meal mockMeal;
    @InjectMocks
    private MealsService mealsService;

    @BeforeEach
    void setUp() {
        when(mockMeal.getCaloricValue()).thenReturn(1);
    }

    @Test
    void reportMeal() {
        when(mealsRepository.save(any(Meal.class))).thenReturn(mockMeal);

        Meal meal = mealsService.reportMeal(mockMeal);
        assertThat(meal).isNotNull();
        assertThat(meal.getCaloricValue()).isEqualTo(1);
    }

    @Test
    void getMeals() {
        when(mealsRepository.findAllByUserId(anyInt()))
                .thenReturn(Optional.of(Collections.singletonList(mockMeal)));

        Optional<List<Meal>> optionalMeals = mealsService.getMeals(1);

        assertThat(optionalMeals.isPresent());

        List<Meal> meals = optionalMeals.get();
        assertThat(meals.size()).isGreaterThan(0);
        assertThat(meals.get(0).getCaloricValue()).isEqualTo(1);
    }
}