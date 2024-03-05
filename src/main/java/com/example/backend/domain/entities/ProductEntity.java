package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class ProductEntity {

    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private double price;
    @ElementCollection
    @CollectionTable(name="photos", joinColumns=@JoinColumn(name="product_id"))
    @Column(name="urls")
    private List<String> photoUrl;
    private LocalDateTime postedDate = LocalDateTime.now();
    private String productCondition;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;
    private String location;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
