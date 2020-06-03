package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.Authorities;
import com.devxschool.food_delivery.repository.AuthoritiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthoritiesService {

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    public List<Authorities> findAll(){
        return authoritiesRepository.findAll();
    }

    public Authorities findByAthority(String authority){
        return authoritiesRepository.findByAuthority(authority);
    }
}
