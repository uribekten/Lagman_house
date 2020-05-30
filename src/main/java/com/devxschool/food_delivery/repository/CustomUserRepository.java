package com.devxschool.food_delivery.repository;

import com.devxschool.food_delivery.models.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long>{
    CustomUser findCustomUserByUsername(String username);
}
