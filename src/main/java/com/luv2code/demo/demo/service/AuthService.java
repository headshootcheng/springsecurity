package com.luv2code.demo.demo.service;

import com.luv2code.demo.demo.modal.request.LoginRequest;
import com.luv2code.demo.demo.modal.request.SignupRequest;
import com.luv2code.demo.demo.modal.response.JwtResponse;

public interface AuthService {
    void signUpUser(SignupRequest signupRequest);

    void signUpAdmin(SignupRequest signupRequest);

    JwtResponse userLogin(LoginRequest loginRequest);
}
