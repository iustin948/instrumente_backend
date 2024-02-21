package com.example.backend.services;

import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.ProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {


    public List<ProductEntity> findProductsByCategory(Long id);
    public CategoryEntity addCategory(CategoryEntity category);
    public CategoryEntity findById(Long id);
    public List<CategoryEntity> findAll();
    public CategoryEntity deleteById(Long id);

}
