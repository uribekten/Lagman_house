package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Cart;
import com.devxschool.food_delivery.models.CartItem;
import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@SessionAttributes("cart")
public class CartContoller {

    @Autowired
    FoodService foodService;

    @GetMapping("/cart/list")
    public String listCartItems(@ModelAttribute("cart") Cart cart){

        return "cart_items";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id, @ModelAttribute("cart") Cart cart){
        Optional<Food> food = foodService.findById(id);

        if (!food.isPresent())
            return "redirect:/";

        CartItem cartItem = new CartItem();
        cartItem.setFood(food.get());
        cartItem.setQuantity(1);

        if (cart.getUuid() != null){
            cart.setUuid(UUID.randomUUID());
            cart.addCartItem(cartItem);
        } else {
            cart.addCartItem(cartItem);
        }

        return "redirect:/";
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCartItem(@PathVariable Long id, @ModelAttribute("cart") Cart cart){
        Optional<Food> food = foodService.findById(id);
        if (!food.isPresent())
            return "redirect:/cart/list";

        CartItem cartItem = new CartItem();
        cartItem.setFood(food.get());
        cart.removeCartItem(cartItem);

        return "redirect:/cart/list";
    }

    @ModelAttribute("cart")
    public Cart cart(){
        return new Cart();
    }
}
