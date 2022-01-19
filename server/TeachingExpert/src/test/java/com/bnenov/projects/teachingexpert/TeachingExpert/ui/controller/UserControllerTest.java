package com.bnenov.projects.teachingexpert.TeachingExpert.ui.controller;

import com.bnenov.projects.teachingexpert.TeachingExpert.dto.UserDto;
import com.bnenov.projects.teachingexpert.TeachingExpert.service.UserService;
import com.bnenov.projects.teachingexpert.TeachingExpert.ui.request.UserRegisterRequestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    ModelMapper modelMapper;
    private AutoCloseable closeable;
    UserDto userDto;

    @BeforeEach
    void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        userController = new UserController(userService, modelMapper);
        userDto = new UserDto();
        userDto.setUserId("asbah");
        userDto.setEmail("test@abv.bg");
        userDto.setPassword("123456");
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }


    @Test
    final void testCreateUser() {
        when(userService.createUser(any())).thenReturn(userDto);

        UserDto createdUser = userController.createUser(new UserRegisterRequestModel());
        assertNotNull(createdUser);
        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertEquals(userDto.getPassword(), createdUser.getPassword());
        assertEquals(userDto.getUserId(), createdUser.getUserId());
    }
}
