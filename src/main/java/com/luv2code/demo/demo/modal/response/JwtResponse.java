package com.luv2code.demo.demo.modal.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private UserInfo userInfo;

    public JwtResponse(String token, UserInfo userInfo) {
        this.token = token;
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "token='" + token + '\'' +
                ", userInfo=" + userInfo +
                '}';
    }
}
