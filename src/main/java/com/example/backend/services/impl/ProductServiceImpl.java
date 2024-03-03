package com.example.backend.services.impl;

import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.repositories.ProductRespository;
import com.example.backend.services.ProductService;
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
    @Override
    public List<ProductEntity> findNew(int N) {
        return null;
    }



    @Override
    public List<ProductEntity> findFirst50New(Long category_id) {
        return productRespository.findFirst50New(category_id);
    }

    @Override
    public ProductDto mapEntityToDto(ProductEntity entity)
    {
        ProductDto dto = new ProductDto();
        dto.setPhotoUrl(entity.getPhotoUrl());
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setSeller(entity.getSeller());
        dto.setTitle(entity.getTitle());
        dto.setCategoryId(entity.getCategory().getId());
        dto.setPostedDate(entity.getPostedDate());
        return dto;
    }

    @Override
    public List<ProductEntity> findProductsBySellerId(Long seller_id) {
        return productRespository.findBySellerId(seller_id);
    }
}
