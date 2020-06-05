package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@Controller
@RequestMapping("/public")
public class PublicFoodController {

    @Autowired
    FoodService foodService;


    @GetMapping("/food/list")
    public String listFoods(Model model){

        model.addAttribute("food", foodService.findAll());
      
        return "food_list";
    }

    @GetMapping("/food/list_by_price")
    public String listFoods(@RequestParam Float price, Model model){
        model.addAttribute("food", foodService.findAllByPrice(price));
        return "food_list";
    }



    @GetMapping("/food/detail")
    public String getFoodDetails(@RequestParam Long id, Model model){
        Optional<Food> food = foodService.findById(id);
        if (!food.isPresent())
            return "not_found";

        model.addAttribute("food", food.get());

        return "food_details";
    }

}
