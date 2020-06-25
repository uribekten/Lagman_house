package com.devxschool.food_delivery.repository;

import com.devxschool.food_delivery.models.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

    List<Food> findAll();

    @Query("select f from Food f where f.price > :price")
    List<Food> findAccordingToPrice(@Param("price") Float price);
}
