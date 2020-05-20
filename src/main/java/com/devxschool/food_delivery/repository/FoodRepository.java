package com.devxschool.food_delivery.repository;

import com.devxschool.food_delivery.models.Food;

import java.util.List;
import java.util.Optional;

public interface FoodRepository {
    int count();
    int save(Food food);
    int update(Food food);
    int deleteById(Long id);
    List<Food> findAll();
    Optional<Food> findById(Long id);
    List<Food> findByFoodType(String fooType);
}
