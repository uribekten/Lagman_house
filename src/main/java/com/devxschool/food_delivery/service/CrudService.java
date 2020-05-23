package com.devxschool.food_delivery.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    Long count();
    void save(T t);
    void delete(T t);
    Optional<T> findById(Long id);
    List<T> findAll();
}
