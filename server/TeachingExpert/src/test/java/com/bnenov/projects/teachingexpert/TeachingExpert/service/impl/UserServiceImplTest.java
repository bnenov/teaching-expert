package com.bnenov.projects.teachingexpert.TeachingExpert.service.impl;

import com.bnenov.projects.teachingexpert.TeachingExpert.dto.UserDto;
import com.bnenov.projects.teachingexpert.TeachingExpert.entity.UserEntity;
import com.bnenov.projects.teachingexpert.TeachingExpert.exception.UsernameAlreadyExistException;
import com.bnenov.projects.teachingexpert.TeachingExpert.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    ModelMapper modelMapper;
    private AutoCloseable closeable;

    @InjectMocks
    UserServiceImpl userService;

    UserEntity userEntity;

    @BeforeEach
    void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        userService = new UserServiceImpl(userRepository, modelMapper);
        userEntity = new UserEntity();
        userEntity.setId(1);
        userEntity.setEmail("test23@abv.bg");
        userEntity.setPassword("123456789");

    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    final void testCreateUser() {

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDto userDto = new UserDto();
        userDto.setEmail("test@abv.bg");
        userDto.setPassword("123456");

        UserDto createdUser = userService.createUser(userDto);
        assertNotNull(createdUser);
        assertEquals("test23@abv.bg", createdUser.getEmail());
        assertEquals("123456789", createdUser.getPassword());
    }

    @Test
    final void testCreateUser_UsernameAlreadyExistsException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDto userDto = new UserDto();
        userDto.setEmail("test@abv.bg");
        userDto.setPassword("123456");

        assertThrows(UsernameAlreadyExistException.class, () -> {
            userService.createUser(userDto);
        });
    }
}
