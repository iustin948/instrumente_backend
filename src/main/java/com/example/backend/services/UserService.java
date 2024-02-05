package com.example.backend.services;

import com.example.backend.domain.entities.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity save(UserEntity userEntity);
    UserEntity findUserById(Long id);
    List<UserEntity> findAll();
    void deleteUser(Long id);

    boolean existsId(Long id);
}
