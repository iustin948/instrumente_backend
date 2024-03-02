package com.example.backend.domain.dto;

import com.example.backend.domain.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private List<String> photoUrl = new ArrayList<>();
    private LocalDateTime postedDate = LocalDateTime.now();
    private String condition;
    private UserEntity seller;
    private Long categoryId;
}
