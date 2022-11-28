package com.example.shopappfront.data.models;

public class AuthenticationModel implements ApplicationModel {

    private User user;
    private String token;

    public AuthenticationModel(User authUser, String token) {
        this.user = authUser;
        this.token = token;
    }

    public User getAuthUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

}
