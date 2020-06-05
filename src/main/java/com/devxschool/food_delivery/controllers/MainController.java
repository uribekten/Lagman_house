package com.devxschool.food_delivery.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getIndex(){
        return "layout";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

}
