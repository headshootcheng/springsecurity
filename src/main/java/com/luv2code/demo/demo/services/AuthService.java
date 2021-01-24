package com.luv2code.demo.demo.services;

import com.luv2code.demo.demo.modals.requests.LoginRequest;
import com.luv2code.demo.demo.modals.requests.SignUpRequest;
import com.luv2code.demo.demo.modals.responses.JwtResponse;

public interface AuthService {
    void signUpUser(SignUpRequest signupRequest);

    void signUpAdmin(SignUpRequest signupRequest);

    JwtResponse userLogin(LoginRequest loginRequest);
}
