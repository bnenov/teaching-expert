package com.bnenov.projects.teachingexpert.TeachingExpert.ui.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterRequestModel {

    private String email;
    private String password;

    public UserRegisterRequestModel() {
    }

    @Email
    @NotNull(message = "E-mail cannot be null!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Password cannot be null!")
    @Size(min = 6, max = 12, message = "Password must be between 6 and 12 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
