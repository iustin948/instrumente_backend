package com.example.backend.services.impl;

import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.repositories.CategoryRepository;
import com.example.backend.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<ProductEntity> findProductsByCategory(Long id) {
        CategoryEntity category = categoryRepository.findById(id) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        List<ProductEntity> products = new ArrayList<>();
        collectProducts(category, products);
        return products;
    }


    @Override
    public CategoryEntity addCategory(CategoryEntity category) {

        return categoryRepository.save(category);
    }

    private void collectProducts(CategoryEntity category, List<ProductEntity> products)
    {
        products.addAll(category.getProducts());
        for(CategoryEntity subcategory : category.getSubcategories())
            collectProducts(subcategory,products);
    }
    public CategoryEntity findById(Long id)
    {
        CategoryEntity category = categoryRepository.findById(id) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return category;
    }

    @Override
    public List<CategoryEntity> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryEntity deleteById(Long id) {
        CategoryEntity category = categoryRepository.findById(id) .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.deleteById(id);
        return category;
    }


}

