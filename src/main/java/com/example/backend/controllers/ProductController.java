package com.example.backend.controllers;

import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.mappers.impl.ProductMapperImpl;
import com.example.backend.services.ProductService;
import org.springframework.boot.autoconfigure.mongo.PropertiesMongoConnectionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    ProductService productService;
    Mapper<ProductEntity, ProductDto> productMapper;
    ProductController(ProductService productService,Mapper<ProductEntity, ProductDto> productMapper)
    {
        this.productService = productService;
        this.productMapper = productMapper;
    }
    @PostMapping(path = "/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @RequestParam("image") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "/home/iustin/Desktop/instrumente_backend/images" + File.separator + fileName;

        File dest = new File(filePath);
        file.transferTo(dest); // Save file to the file system
        productDto.setPhotoUrl(filePath);

        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity productEntityFromDatabase = productService.save(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(productEntityFromDatabase), HttpStatus.CREATED);
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto)
    {
        productDto.setId(id);
        ProductEntity productEntity = productMapper.mapFrom(productDto);
        ProductEntity productEntityFromDatabase = productService.save(productEntity);
        return new ResponseEntity<>(productMapper.mapTo(productEntityFromDatabase), HttpStatus.OK);
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.delete(id);
        try {
            productService.delete(id);
            return ResponseEntity.ok("Resource with id " + id + " deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting resource with id " + id);
        }
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductDto> > findAll()
    {
        List<ProductEntity> list = productService.findAll();
        return new ResponseEntity<>( list.stream().map(productMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "products/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id)
    {
        ProductEntity productEntity= productService.findProductById(id);
        return new ResponseEntity<>(productMapper.mapTo(productEntity), HttpStatus.OK);
    }

    @GetMapping(path = "products/new")
    public ResponseEntity<List<ProductDto> > findNew50(@RequestParam(required = false) String category)
    {
        List<ProductEntity> list = productService.findFirst50New(category);
        return new ResponseEntity<>( list.stream().map(productMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }
}
