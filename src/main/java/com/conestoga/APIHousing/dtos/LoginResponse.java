package com.conestoga.APIHousing.dtos;


public class LoginResponse {
    private String token;
    private AccountDTO account;

    public LoginResponse(String token, AccountDTO account) {
        this.token = token;
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public AccountDTO getAccount() {
        return account;
    }
}
