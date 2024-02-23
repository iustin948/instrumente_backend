package com.example.backend.controllers;

import com.example.backend.domain.dto.CredentialsDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.services.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto)
    {
       return null;
    }
}
