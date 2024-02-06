package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class ProductEntity {

    private @Id @GeneratedValue Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private Date postedDate;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;
}
