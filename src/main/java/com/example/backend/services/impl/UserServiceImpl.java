package com.example.backend.services.impl;

import com.example.backend.domain.entities.UserEntity;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public void login()
    {

    }
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findUserById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public List<UserEntity> findAll() {
        return new ArrayList<>(userRepository
                .findAll());
    }



    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public boolean existsId(Long id) {
        return userRepository.existsById(id);
    }

}
