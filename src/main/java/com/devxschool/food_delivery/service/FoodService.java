package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("foodService")
public class FoodService implements CrudService<Food> {

    @Autowired
    FoodRepository foodRepository;

    @Override
    public Long count() {
        return foodRepository.count();
    }

    @Override
    public void save(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void delete(Food food) {
        foodRepository.delete(food);
    }

    @Override
    public Optional<Food> findById(Long id) {
        return foodRepository.findById(id);
    }

    @Override
    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public List<Food> findAllByPrice(Float price) {
        return foodRepository.findAccordingToPrice(price);
    }
}
