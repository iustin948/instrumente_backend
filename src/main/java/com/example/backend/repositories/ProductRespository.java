package com.example.backend.repositories;

import com.example.backend.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<ProductEntity,Long> {
    @Query(value = "SELECT * FROM product_entity p WHERE (:category is null OR p.category = :category) ORDER BY p.posted_date ASC LIMIT 50", nativeQuery = true)
    List<ProductEntity> findFirst50New(String category);
}
