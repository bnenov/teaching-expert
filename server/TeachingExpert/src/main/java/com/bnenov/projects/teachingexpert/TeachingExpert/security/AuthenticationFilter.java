package com.bnenov.projects.teachingexpert.TeachingExpert.security;

import com.bnenov.projects.teachingexpert.TeachingExpert.ui.request.UserLoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//
//            UserLoginRequestModel userLoginRequestModel = new ObjectMapper().readValue(request.getInputStream(),
//                    UserLoginRequestModel.class);
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
}
