package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.models.Orders;
import com.devxschool.food_delivery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void placeOrder(Orders order) {
        orderRepository.save(order);
    }

    @Override
    public List<Orders> orderList() {
        return orderRepository.findAll();
    }

    @Override
    public List<Orders> userOrderList(CustomUser customUser) {

        return orderRepository.findAllByCustomUser(customUser);
    }

    @Override
    public void updateOrder(Orders order) {

    }

    @Override
    public void cancelOrder(Orders order) {

    }
}
