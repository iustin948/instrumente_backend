package com.example.backend.controllers;

import com.example.backend.domain.dto.CategoryDto;
import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

        {
            CategoryEntity categoryEntity = categoryMapper.mapFrom(categoryDto);
            CategoryEntity categoryEntityFromDatabase = categoryService.addCategory(categoryEntity);
            return new ResponseEntity<>(categoryMapper.mapTo(categoryEntityFromDatabase), HttpStatus.CREATED);
        }
    }
    @PutMapping(path = "/category/{id}")
    public ResponseEntity<CategoryDto> modifyCategory(@RequestBody CategoryDto category, @PathVariable Long id)
    {
        category.setId(id);
        CategoryEntity categoryEntity = categoryMapper.mapFrom(category);
        categoryService.addCategory(categoryEntity);
        CategoryEntity categoryEntityFromDatabase = categoryService.findById(id);
        return new ResponseEntity<>(categoryMapper.mapTo(categoryEntityFromDatabase), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/category/{id}")
    public void delete(@PathVariable Long id)
    {
        categoryService.deleteById(id);
    }

    @GetMapping(path = "/category")
    public ResponseEntity<List<CategoryDto>> findAll()
    {
        List<CategoryEntity> list = categoryService.findAll();
        return new ResponseEntity<>( list.stream().map(categoryMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "/category/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable Long id)
    {
        CategoryEntity category= categoryService.findById(id);
        return new ResponseEntity<>(categoryMapper.mapTo(category),HttpStatus.OK);
    }

}
