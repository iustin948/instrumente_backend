package com.example.backend.services;

import com.example.backend.domain.entities.ProductEntity;

import java.util.List;

public interface ProductService {

    public ProductEntity save(ProductEntity productEntity);
    public void delete(Long id);
    public List<ProductEntity> findAll();
    public ProductEntity findProductById(Long id);
}
