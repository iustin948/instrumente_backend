package com.example.backend.mappers.impl;

import com.example.backend.domain.dto.SignUpDto;
import com.example.backend.domain.dto.UserDto;
import com.example.backend.domain.entities.UserEntity;
import com.example.backend.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {
    private final ModelMapper modelMapper;
    public UserMapperImpl(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }
    @Override
    public UserDto mapTo(UserEntity userEntity) {
        return modelMapper.map(userEntity,UserDto.class);
    }

    @Override
    public UserEntity mapFrom(UserDto userDto) {
        return modelMapper.map(userDto,UserEntity.class);
    }

    public UserDto mapSignUpToEntity(SignUpDto signUpDto){
        UserDto userDto = new UserDto();
        userDto.setFirstName(signUpDto.firstName());
        return userDto;
    }
}
