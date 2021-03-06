package com.devxschool.food_delivery.repository;

import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    List<Orders> findAllByCustomUser(CustomUser customUser);
}
