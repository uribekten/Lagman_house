package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Cart;
import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.models.Orders;
import com.devxschool.food_delivery.models.PaymentStatus;
import com.devxschool.food_delivery.service.AuthService;
import com.devxschool.food_delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    AuthService authService;

    @GetMapping("/place")
    public String placeOrder(@ModelAttribute("cart") Cart cart, Principal principal, Model model){

        if (principal == null)
            return "redirect:/login";

        if (cart == null)
            return "redirect:/";

        CustomUser customUser = authService.findUserByUsername(cart.getUsername());
        LocalDateTime currentTime = LocalDateTime.now();
        Orders orders = new Orders();
        orders.setCustomUser(customUser);
        orders.setCartItemList(cart.getCartItems());
        orders.setOrderPlacedAt(currentTime);
        orders.setOrderStatus(Orders.OrderStatus.CONFIRMED);
        orders.setOrderUpdatedAt(currentTime);

        orderService.placeOrder(orders);
        cart.clearItems();
        return "redirect:list";
    }

    @GetMapping("/list")
    public String getOrderList(Authentication authentication, Principal principal, Model model){
        if (authentication == null || !authentication.isAuthenticated())
            return "redirect:/";

        CustomUser customUser = authService.findUserByUsername(principal.getName().toString());
        model.addAttribute("orders", orderService.userOrderList(customUser));
        if (authentication.getAuthorities().contains("ADMIN"))
            return "orders_admin";
        else
            return "orders";
    }


}
