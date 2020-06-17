package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.models.Orders;

import java.util.List;

public interface OrderService{

    void placeOrder(Orders order);
    List<Orders> orderList();
    List<Orders> userOrderList(CustomUser customUser);
    void updateOrder(Orders order);
    void cancelOrder(Orders order);
}
