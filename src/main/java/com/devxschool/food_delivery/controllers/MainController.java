package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    FoodService foodService;

    @GetMapping("/")
    public String getIndex(Model model){

        model.addAttribute("foodTypes", Food.FoodType.values());
        model.addAttribute("foodList", foodService.findAll());
        return "food_shopping";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

}
