package com.example.backend.repositories;

import com.example.backend.domain.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRespository extends JpaRepository<ProductEntity,Long> {
    @Query(value = "SELECT * FROM product_entity p WHERE (:category_id is null OR p.category_id = :category_id) ORDER BY p.posted_date ASC LIMIT 50", nativeQuery = true)
    List<ProductEntity> findFirst50New(Long category_id);

    @Query(value = "SELECT * FROM product_entity where seller_id = :seller_id", nativeQuery = true)
    List<ProductEntity> findBySellerId(@Param("seller_id") Long seller_id);
}
