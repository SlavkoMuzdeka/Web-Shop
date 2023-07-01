package com.example.projektni_ip.controllers;

import com.example.projektni_ip.entities.dto.SingleAttribute;
import com.example.projektni_ip.entities.dto.SingleCategory;
import com.example.projektni_ip.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<SingleCategory> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}/attributes")
    public List<SingleAttribute> findAllAttributes(@PathVariable Integer id) {
        return categoryService.findAllAttributes(id);
    }

}
