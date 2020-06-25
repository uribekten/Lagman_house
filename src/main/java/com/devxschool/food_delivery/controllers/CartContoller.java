package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Cart;
import com.devxschool.food_delivery.models.CartItem;
import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.service.AuthService;
import com.devxschool.food_delivery.service.FoodService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
@SessionAttributes("cart")
public class CartContoller {

    @Autowired
    FoodService foodService;

    @Autowired
    AuthService authService;

    @Value("${stripe.keys.secret}")
    private String strpeSecretKey;

    @Value("${stripe.keys.public}")
    private String stripePublicKey;


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

    @GetMapping("/cart/checkout")
    public String cartCheckout(@ModelAttribute("cart") Cart cart, Model model, Authentication authentication, Principal principal){
        if (authentication == null || !authentication.isAuthenticated())
            return "redirect:/login";


        Stripe.apiKey = strpeSecretKey;

        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setCurrency("usd")
                        .setAmount(cart.getTotalPrice().longValue() * 100L)
                        // Verify your integration in this guide by including this parameter
                        .putMetadata("integration_check", "accept_a_payment")
                        .build();

        try {
            PaymentIntent intent = PaymentIntent.create(params);
            model.addAttribute("client_secret", intent.getClientSecret());
        } catch (StripeException e) {
            e.printStackTrace();
        }

        CustomUser customUser = authService.findUserByUsername(principal.getName());
        cart.setUsername(customUser.getUsername());
        model.addAttribute("user", customUser);
        model.addAttribute("stripePublicKey", stripePublicKey);

        return "cart_checkout_new";
    }

    @GetMapping("/cart/payment_success")
    public String paymentSuccess(){
        return "redirect:/order/place";
    }

    @ModelAttribute("cart")
    public Cart cart(){
        return new Cart();
    }
}
