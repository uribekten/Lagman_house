package com.devxschool.food_delivery.controllers;

import com.devxschool.food_delivery.models.Images;
import com.devxschool.food_delivery.service.AwsS3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FileHandlerController {

    @Autowired
    AwsS3ServiceImpl awsS3Service;

    @GetMapping("/admin/images")
    public String showUploadForm(Model model){
        List<Images> imagesList = awsS3Service.listAll();
        model.addAttribute("images", imagesList);
        return "images_upload";
    }

    @PostMapping("/admin/images")
    public String uploadImage(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        awsS3Service.upload(file);
        return "redirect:/admin/images";
    }

    @GetMapping("/admin/images/delete")
    public String deleteImage(@RequestParam String key){
        awsS3Service.delete(key);
        return "redirect:/admin/images";
    }



}
