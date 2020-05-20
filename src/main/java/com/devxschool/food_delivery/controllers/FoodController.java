package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.models.FoodType;
import com.devxschool.food_delivery.repository.JdbcFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FoodController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("jdbcFoodRepository")
    JdbcFoodRepository foodRepository;

    @GetMapping("/")
    public String getIndex(){
        return "layout";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

    @GetMapping("/food/list")
    public String listFoods(Model model){

        model.addAttribute("food", foodRepository.findAll());
      
        return "food_list";
    }

    @GetMapping("/food/new")
    public String createFood(Model model){
        model.addAttribute("foodType", FoodType.values());
        model.addAttribute("newFood",new Food());
        return "create_food";
    }

    @GetMapping("/food/update")
    public String updateFood(@RequestParam Long id, Model model){
        Optional<Food> food = foodRepository.findById(id);
        if (!food.isPresent())
            return "redirect:/food/new";

        model.addAttribute("newFood",food.get());
        model.addAttribute("foodType", FoodType.values());
        return "create_food";
    }

    @PostMapping("/food")
    public String createFood(@ModelAttribute Food food){
        Optional<Food> oldFood = foodRepository.findById(food.getId());
        if (oldFood.isPresent()){
            foodRepository.update(food);
        } else {
            foodRepository.save(food);
        }
            
        return "redirect:/food/list";
    }

    @GetMapping("/food/delete")
    public String deleteFood(@RequestParam Long id) {
        foodRepository.deleteById(id);
            
        return "redirect:/food/list";
    }

    @GetMapping("/food/detail")
    public String getFoodDetails(@RequestParam Long id, Model model){
        Optional<Food> food = foodRepository.findById(id);
        if (!food.isPresent())
            return "not_found";

        model.addAttribute("food", food.get());

        return "food_details";
    }

}
