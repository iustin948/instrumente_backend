package com.example.backend.controllers;

import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.mappers.impl.UserMapperImpl;
import com.example.backend.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;
    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper)
    {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping(path = "/user")
    public UserDto createUser(@RequestBody UserDto user)
    {
        UserEntity userEntity = userMapper.mapFrom(user);
        System.out.println(userEntity.toString());
        UserEntity userEntitySavedInDataBase = userService.createUser(userEntity);
        return userMapper.mapTo(userEntity);

    }
}
