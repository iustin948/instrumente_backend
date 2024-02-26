package com.example.backend.controllers;

import com.example.backend.domain.dto.CredentialsDto;
import com.example.backend.domain.dto.SignUpDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.services.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
        UserDto user = userService.login(credentialsDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto)
    {
        UserDto userDto = userService.register(signUpDto);
        return ResponseEntity.created(URI.create("/users" + userDto.getId())).body(userDto);
    }
}
