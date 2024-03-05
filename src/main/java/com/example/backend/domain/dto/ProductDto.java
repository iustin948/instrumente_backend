package com.example.backend.domain.dto;

import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.domain.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private List<String> photoUrl = new ArrayList<>();
    private LocalDateTime postedDate = LocalDateTime.now();
    private String productCondition;
    private Long seller;
    private Long categoryId;
    private String location;
    private List<ProductEntity> favorite;
}
