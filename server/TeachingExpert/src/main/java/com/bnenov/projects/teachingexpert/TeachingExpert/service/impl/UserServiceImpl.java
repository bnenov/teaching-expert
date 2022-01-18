package com.bnenov.projects.teachingexpert.TeachingExpert.service.impl;

import com.bnenov.projects.teachingexpert.TeachingExpert.dto.UserDto;
import com.bnenov.projects.teachingexpert.TeachingExpert.entity.UserEntity;
import com.bnenov.projects.teachingexpert.TeachingExpert.repository.UserRepository;
import com.bnenov.projects.teachingexpert.TeachingExpert.service.UserService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        UserEntity existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            // ToDo Add proper exception
            throw new RuntimeException("User already exists!");
        }

        user.setUserId(UUID.randomUUID().toString());

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }
}
