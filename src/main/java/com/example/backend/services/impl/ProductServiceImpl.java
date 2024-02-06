package com.example.backend.services.impl;

import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.repositories.ProductRespository;
import com.example.backend.services.ProductService;
import com.example.backend.services.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    ProductRespository productRespository;
    ProductServiceImpl(ProductRespository productRespository)
    {
        this.productRespository = productRespository;
    }
    @Override
    public ProductEntity save(ProductEntity productEntity) {
       return  productRespository.save(productEntity);
    }

    @Override
    public void delete(Long id) {
         productRespository.deleteById(id);
    }

    @Override
    public List<ProductEntity> findAll() {
        return productRespository.findAll();
    }

    @Override
    public ProductEntity findProductById(Long id) {
        Optional<ProductEntity> byId =  productRespository.findById(id);
        return byId.orElse(null);
    }
}
