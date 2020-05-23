package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.Country;
import com.devxschool.food_delivery.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("countryService")
public class CountryService implements CrudService<Country> {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Long count() {
        return countryRepository.count();
    }

    @Override
    public void save(Country country) {

    }

    @Override
    public void delete(Country country) {

    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
