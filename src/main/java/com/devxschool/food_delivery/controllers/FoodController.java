package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Food;
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

    private List<Food> foodList = new ArrayList<>();

    @GetMapping("/")
    public String getIndex(){
        return "layout";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

    @GetMapping("/foods")
    public String listFoods(Model model){

        model.addAttribute("food", foodList);
        model.addAttribute("newFood", new Food());
        model.addAttribute("foodType", Food.FoodType.values());
        return "food_list";
    }

    @PostMapping("/foods")
    public String createFood(@ModelAttribute Food food){
        foodList.add(food);
        return "redirect:/foods";
    }

    @GetMapping("/food_details")
    public String getFoodDetails(@RequestParam Long id, Model model){
        Optional<Food> food = foodList.stream().filter(f -> f.getId() == id).findFirst();
        if (!food.isPresent())
            return "not_found";

        model.addAttribute("food", food.get());

        return "food_details";
    }

}
