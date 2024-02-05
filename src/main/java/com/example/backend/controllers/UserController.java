package com.example.backend.controllers;

import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;
    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper)
    {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping(path = "/users")
    public UserDto createUser(@RequestBody UserDto user)
    {
        UserEntity userEntity = userMapper.mapFrom(user);
        System.out.println(userEntity.toString());
        UserEntity userEntitySavedInDataBase = userService.save(userEntity);
        return userMapper.mapTo(userEntity);

    }

    @PutMapping(path = "users/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id)
    {
        if(!userService.existsId(id)) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userDto.setId(id);
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity userEntitySavedInDataBase = userService.save(userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(userEntitySavedInDataBase),HttpStatus.OK
        );
    }

    @GetMapping(path = "/users/{id}")
    public UserDto getUser(@PathVariable Long id)
    {
        UserEntity userEntity = userService.findUserById(id);
        return userMapper.mapTo(userEntity);
    }

    @GetMapping(path = "users")
    public List<UserDto> findAll()
    {
        List<UserEntity> list = userService.findAll();
        return list.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @DeleteMapping(path = "users/{id}")
    void deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
    }
}
