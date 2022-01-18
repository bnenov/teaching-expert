package com.bnenov.projects.teachingexpert.TeachingExpert.ui.request;

public class UserRegisterRequestModel {

    private String email;
    private String password;

    public UserRegisterRequestModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
