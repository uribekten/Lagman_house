package com.devxschool.food_delivery.service;

import com.devxschool.food_delivery.models.Images;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CloudService {
    void init();
    void upload(MultipartFile multipartFile);
    List<Images> listAll();
    void delete(String key);
}
