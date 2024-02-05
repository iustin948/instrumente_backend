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
    private String first_name;
    private String second_name;
    private String email;
    private String phoneNumber;
    private Date registrationDate;
    private float rating;
}
