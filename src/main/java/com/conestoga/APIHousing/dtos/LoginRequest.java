package com.conestoga.APIHousing.dtos;

public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //override toString method
    @Override
    public String toString() {
        return "LoginRequest [password=" + password + ", username=" + username + "]";
    }
}
