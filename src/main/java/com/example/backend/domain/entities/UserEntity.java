package com.example.backend.domain.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String first_name;
    private String second_name;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
    private float rating;



}
