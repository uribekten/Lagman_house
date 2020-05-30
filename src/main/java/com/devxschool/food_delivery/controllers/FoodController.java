package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Country;
import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.service.CountryService;
import com.devxschool.food_delivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FoodController {

    @Autowired
    FoodService foodService;

    @Autowired
    @Qualifier("countryService")
    CountryService countryService;

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

        model.addAttribute("food", foodService.findAll());
      
        return "food_list";
    }

    @GetMapping("/food/list_by_price")
    public String listFoods(@RequestParam Float price, Model model){
        model.addAttribute("food", foodService.findAllByPrice(price));
        return "food_list";
    }

    @GetMapping("/food/new")
    public String createFood(Model model, Authentication authentication){
     
        model.addAttribute("foodType", Food.FoodType.values());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("newFood",new Food());
        return "create_food";
    }

    @GetMapping("/food/update")
    public String updateFood(@RequestParam Long id, Model model){
        Optional<Food> food = foodService.findById(id);
        if (!food.isPresent())
            return "redirect:/food/new";

        model.addAttribute("newFood",food.get());
        model.addAttribute("foodType", Food.FoodType.values());
        return "create_food";
    }

    @PostMapping("/food")
    public String createFood(@ModelAttribute Food food){
        foodService.save(food);
            
        return "redirect:/food/list";
    }

    @GetMapping("/food/delete")
    public String deleteFood(@RequestParam Long id) {
        Optional<Food> oldFood = foodService.findById(id);
        oldFood.ifPresent(food -> foodService.delete(food));
            
        return "redirect:/food/list";
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
