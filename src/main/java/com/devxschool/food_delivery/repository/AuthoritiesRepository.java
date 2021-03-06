package com.devxschool.food_delivery.repository;

import com.devxschool.food_delivery.models.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

    Authorities findByAuthority(String authority);
}
