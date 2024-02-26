package com.example.backend.services;

import com.example.backend.domain.dto.CredentialsDto;
import com.example.backend.domain.dto.SignUpDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity save(UserEntity userEntity);
    UserEntity findUserById(Long id);
    List<UserEntity> findAll();
    void deleteUser(Long id);

    boolean existsId(Long id);
    public UserDto login(CredentialsDto credentialsDto);
    public UserDto register(SignUpDto signUpDto);
}
