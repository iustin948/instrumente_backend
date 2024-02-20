package com.example.backend.controllers;

import com.example.backend.domain.dto.CategoryDto;
import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    CategoryService categoryService;
    Mapper<CategoryEntity, CategoryDto> categoryMapper;
    CategoryController(CategoryService categoryService, Mapper<CategoryEntity, CategoryDto> categoryMapperMapper)
    {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapperMapper;
    }

    @PostMapping(path = "/category")
    public ResponseEntity<CategoryDto> addNewCategory(@RequestBody CategoryDto categoryDto)
    {
        CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
        CategoryEntity categoryEntityFromDatabase = categoryService.addCategory(categoryEntity);
        return new ResponseEntity<>(categoryMapper.mapTo(categoryEntityFromDatabase), HttpStatus.CREATED);
    }
}
