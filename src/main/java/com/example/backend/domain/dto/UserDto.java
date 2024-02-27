package com.example.backend.domain.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String token;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
    private float rating;
}
