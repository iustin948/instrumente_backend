package com.example.backend.services;

import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.ProductEntity;

import java.util.List;

public interface CategoryService {


    public List<ProductEntity> findProductsByCategory(Long id);
    CategoryEntity addCategory(CategoryEntity category);
}
