package com.conestoga.APIHousing.dtos;

import org.springframework.security.core.userdetails.UserDetails;

public class LoginResponse {
    private String token;
    private UserDetails userDetails;

    public LoginResponse(String token, UserDetails userDetails2) {
        this.token = token;
        this.userDetails = userDetails2;
    }

    public String getToken() {
        return token;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}
