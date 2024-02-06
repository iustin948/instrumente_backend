package com.example.backend.controllers;

import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.mappers.impl.ProductMapperImpl;
import com.example.backend.services.ProductService;
import org.springframework.boot.autoconfigure.mongo.PropertiesMongoConnectionDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    ProductService productService;
    Mapper<ProductEntity, ProductDto> productMapper;
    ProductController(ProductService productService,Mapper<ProductEntity, ProductDto> productMapper)
    {
        this.productService = productService;
        this.productMapper = productMapper;
    }
    @PostMapping(path = "/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto)
    {
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity productEntityFromDatabase = productService.save(productEntity);
        return productMapper.mapTo(productEntityFromDatabase);
    }

    @PutMapping(path = "/products/{id}")
    public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
    {
        productDto.setId(id);
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity productEntityFromDatabase = productService.save(productEntity);
        return productMapper.mapTo(productEntityFromDatabase);
    }

    @DeleteMapping(path = "/products/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.delete(id);
    }

    @GetMapping(path = "products")
    public List<ProductDto> findAll()
    {
        List<ProductEntity> list = productService.findAll();
        return list.stream().map(productMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "products/{id}")
    public ProductDto findById(@PathVariable Long id)
    {
        ProductEntity productEntity= productService.findProductById(id);
        return productMapper.mapTo(productEntity);
    }

}
