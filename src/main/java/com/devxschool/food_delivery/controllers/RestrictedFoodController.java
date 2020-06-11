package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.service.AwsS3ServiceImpl;
import com.devxschool.food_delivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@Secured({"ADMIN"})
public class RestrictedFoodController {

    final private String LIST_URL = "/food/list";
    final private String NEW_URL = "/food/new";
    final private String EDIT_URL = "/food/update";
    final private String DELETE_URL = "/food/delete";

    @Autowired
    FoodService foodService;

    @Autowired
    AwsS3ServiceImpl awsS3Service;

    @GetMapping(LIST_URL)
    public String listFoods(Model model){

        model.addAttribute("food", foodService.findAll());

        return "food_list";
    }

    @GetMapping(NEW_URL)
    public String createFood(Model model, Authentication authentication){

        model.addAttribute("foodType", Food.FoodType.values());
        model.addAttribute("newFood",new Food());
        model.addAttribute("images", awsS3Service.listAll());
        return "create_food";
    }

    @PostMapping(NEW_URL)
    public String createFood(@ModelAttribute Food food){
        foodService.save(food);

        return "redirect:"+LIST_URL;
    }



    @GetMapping(EDIT_URL)
    public String updateFood(@RequestParam Long id, Model model){
        Optional<Food> food = foodService.findById(id);
        if (!food.isPresent())
            return "redirect:"+NEW_URL;

        model.addAttribute("images", awsS3Service.listAll());
        model.addAttribute("newFood",food.get());
        model.addAttribute("foodType", Food.FoodType.values());
        return "create_food";
    }


    @GetMapping(DELETE_URL)
    public String deleteFood(@RequestParam Long id) {
        Optional<Food> oldFood = foodService.findById(id);
        oldFood.ifPresent(food -> foodService.delete(food));

        return "redirect:"+LIST_URL;
    }

}
