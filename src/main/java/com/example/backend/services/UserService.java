package com.example.backend.services;

import com.example.backend.domain.entities.UserEntity;

public interface UserService {
    UserEntity createUser(UserEntity userEntity);

}
