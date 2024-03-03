package com.example.backend.services.impl;

import com.example.backend.domain.dto.CredentialsDto;
import com.example.backend.domain.dto.SignUpDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.exceptions.AppException;
import com.example.backend.mappers.impl.UserMapperImpl;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository  userRepository;
    final   PasswordEncoder passwordEncoder;
    final UserMapperImpl userMapper;

    @Override
    public UserDto login(CredentialsDto credentialsDto)
    {
        UserEntity user = userRepository.findByEmail(credentialsDto.email())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword()))
            return userMapper.mapTo(user);
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(signUpDto.email());

        if(userEntity.isPresent())
        {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = userMapper.mapFrom(userMapper.mapSignUpToEntity(signUpDto));
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        user.setRegistrationDate(new Date());
        UserEntity savedUser = userRepository.save(user);
        return userMapper.mapTo(savedUser);
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
    @Transactional
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
