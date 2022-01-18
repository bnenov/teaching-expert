package com.bnenov.projects.teachingexpert.TeachingExpert.ui.controller;

import com.bnenov.projects.teachingexpert.TeachingExpert.dto.UserDto;
import com.bnenov.projects.teachingexpert.TeachingExpert.service.UserService;
import com.bnenov.projects.teachingexpert.TeachingExpert.ui.request.UserRegisterRequestModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserRegisterRequestModel userRegisterRequestModel) {

        UserDto userDto = modelMapper.map(userRegisterRequestModel, UserDto.class);

        return userService.createUser(userDto);
    }

}
