package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Authorities;
import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.models.RegistrationData;
import com.devxschool.food_delivery.service.AuthService;
import com.devxschool.food_delivery.service.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Controller
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/register")
    public String registrationForm(Model model){
        model.addAttribute("registrationData", new RegistrationData());
        return "registration";
    }

    @PostMapping("/user/register")
    public String registerUser(RegistrationData registrationData){
        CustomUser customUser = registrationData.toCustomUser(passwordEncoder);
        Authorities authorities = authoritiesService.findByAthority("USER");
        customUser.setAuthorities(Arrays.asList(authorities));
        authService.registerUser(customUser);
        return "redirect:/login";
    }

}
