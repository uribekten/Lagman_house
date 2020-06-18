package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.CustomUser;
import com.devxschool.food_delivery.repository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws BadCredentialsException {
        return customUserRepository.findCustomUserByUsername(username);
    }

    public void registerUser(CustomUser customUser){
        customUserRepository.save(customUser);
    }

    public CustomUser findUserByUsername(String username){
        return customUserRepository.findCustomUserByUsername(username);
    }
}
