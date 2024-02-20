package com.example.backend.domain.dto;

import com.example.backend.domain.entities.CategoryEntity;
import com.example.backend.domain.entities.ProductEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private List<ProductEntity> products;
    private CategoryEntity parentCategory;
    private List<CategoryEntity> subcategories;
}
