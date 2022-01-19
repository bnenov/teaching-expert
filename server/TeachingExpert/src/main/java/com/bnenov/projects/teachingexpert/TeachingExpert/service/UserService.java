package com.bnenov.projects.teachingexpert.TeachingExpert.service;

import com.bnenov.projects.teachingexpert.TeachingExpert.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
    UserDto getUserByEmail(String email);
}
