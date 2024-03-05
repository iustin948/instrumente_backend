package com.example.backend.mappers.impl;

import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;

@Component
public class ProductMapperImpl implements Mapper<ProductEntity, ProductDto> {
    ModelMapper modelMapper;

    ProductMapperImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }
    @Override
    public ProductDto mapTo(ProductEntity productEntity) {
        modelMapper.typeMap(ProductEntity.class, ProductDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getSeller().getId(), ProductDto::setSeller);});
        return modelMapper.map(productEntity, ProductDto.class);
    }

    @Override
    public ProductEntity mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, ProductEntity.class);
    }



}
