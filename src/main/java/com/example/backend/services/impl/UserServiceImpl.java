package com.example.backend.services.impl;

import com.example.backend.domain.dto.CredentialsDto;
import com.example.backend.domain.dto.SignUpDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.Mapper;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    Mapper<UserEntity,UserDto> userMapper;

    @Override
    public UserDto login(CredentialsDto credentialsDto)
    {
        UserEntity user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword()))
            return userMapper.mapTo(user);
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> userEntity = userRepository.findByLogin(signUpDto.login());

        if(userEntity.isPresent())
        {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

       return null;
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
