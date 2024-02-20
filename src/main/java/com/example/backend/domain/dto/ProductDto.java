package com.example.backend.domain.dto;

import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String photoUrl;
    private LocalDateTime postedDate = LocalDateTime.now();
    private UserEntity seller;
    private CategoryEntity category;
}
