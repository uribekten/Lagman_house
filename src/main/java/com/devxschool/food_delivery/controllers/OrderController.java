package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.PaymentStatus;
import com.devxschool.food_delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(){
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<PaymentStatus> responseEntity = restTemplate.getForEntity("", PaymentStatus.class);

        PaymentStatus paymentStatus = restTemplate.getForObject("https://lagmanhouse.free.beeceptor.com/payment", PaymentStatus.class);
        System.out.println("!!!!Status "+paymentStatus.getStatus());
        return "redirect:/";
    }
}
