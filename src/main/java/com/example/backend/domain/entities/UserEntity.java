package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
    private float rating;




}
