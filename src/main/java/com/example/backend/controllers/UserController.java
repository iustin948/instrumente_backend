package com.example.backend.controllers;

import com.example.backend.domain.dto.ProductDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.ProductEntity;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.mappers.Mapper;
import com.example.backend.services.ProductService;
import com.example.backend.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final Mapper<UserEntity, UserDto> userMapper;
    private final ProductService productService;
    private final Mapper<ProductEntity, ProductDto> productMapper;

    @PostMapping(path = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user)
    {
        UserEntity userEntity = userMapper.mapFrom(user);
        System.out.println(userEntity.toString());
        UserEntity userEntitySavedInDataBase = userService.save(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(userEntity),HttpStatus.OK);


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

    @PutMapping(path = "addFavorite/{id}")
    public ResponseEntity<UserDto> addFavorite(@RequestBody Long productId, @PathVariable Long id)
    {
        if(!userService.existsId(id)) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity userEntity = userService.findUserById(id);

        Iterator<ProductEntity> iterator = userEntity.getFavorite().iterator();
        while (iterator.hasNext()) {
            ProductEntity product = iterator.next();
            if (product.getId().equals(productId)) {
                iterator.remove();
                UserEntity userEntitySavedInDataBase = userService.save(userEntity);
                return new ResponseEntity<>(
                        userMapper.mapTo(userEntitySavedInDataBase),HttpStatus.OK);

            }
        }

        userEntity.getFavorite().add(productService.findProductById(productId));
        UserEntity userEntitySavedInDataBase = userService.save(userEntity);
        return new ResponseEntity<>(
                userMapper.mapTo(userEntitySavedInDataBase),HttpStatus.OK
        );
    }

    @GetMapping(path = "/favorites/{id}")
    public  ResponseEntity<List<ProductDto> > favorites(@PathVariable Long id)
    {
       List<ProductEntity> favoriteProducts= productService.findByUserFavorite(id);
       return new ResponseEntity<>(favoriteProducts.stream().map(productMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id)
    {
        UserEntity userEntity = userService.findUserById(id);
        return new ResponseEntity<>(userMapper.mapTo(userEntity), HttpStatus.OK);
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
