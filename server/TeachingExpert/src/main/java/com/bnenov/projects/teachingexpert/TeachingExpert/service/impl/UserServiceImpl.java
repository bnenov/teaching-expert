package com.bnenov.projects.teachingexpert.TeachingExpert.service.impl;

import com.bnenov.projects.teachingexpert.TeachingExpert.dto.UserDto;
import com.bnenov.projects.teachingexpert.TeachingExpert.entity.UserEntity;
import com.bnenov.projects.teachingexpert.TeachingExpert.exception.UsernameAlreadyExistException;
import com.bnenov.projects.teachingexpert.TeachingExpert.repository.UserRepository;
import com.bnenov.projects.teachingexpert.TeachingExpert.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity user = modelMapper.map(userDto, UserEntity.class);
        UserEntity existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            // ToDo Add proper exception
            throw new UsernameAlreadyExistException("User already exists!");
        }

        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return modelMapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return modelMapper.map(userRepository.findByEmail(email), UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User does not exist!");
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
