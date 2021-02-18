package com.luv2code.demo.demo.controllers;

import com.luv2code.demo.demo.exceptions.ValidationErrorException;
import com.luv2code.demo.demo.modals.requests.LoginRequest;
import com.luv2code.demo.demo.modals.requests.SignUpRequest;
import com.luv2code.demo.demo.modals.responses.AuthMessageResponse;
import com.luv2code.demo.demo.modals.responses.JwtResponse;
import com.luv2code.demo.demo.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@Tag(name = "AuthController" , description = "Authentication API")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

//    @GetMapping("/test")
//    public String test(){
//        return "test";
//    }
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/preAuthorize")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
//    public String preauthorize(){
//        return "preauthorize";
//    }

    @PostMapping("/signUp")
    @Operation(summary = "Sign Up for User Account")
    public ResponseEntity<AuthMessageResponse> registerUser(@Valid @RequestBody SignUpRequest signupRequest, BindingResult theBindingResult){
        logger.info("SignUp Request: {}", signupRequest.toString());
        if(theBindingResult.hasErrors()){
            throw new ValidationErrorException(theBindingResult.getFieldError().getDefaultMessage());
        }
        authService.signUpUser(signupRequest);
        return ResponseEntity.ok(new AuthMessageResponse(HttpStatus.ACCEPTED.value(), "User registered successfully", System.currentTimeMillis(), false));
    }

    @PostMapping("/signUpAdmin")
    @Operation(summary = "Sign Up for Admin Account")
    public ResponseEntity<AuthMessageResponse> registerAdmin(@Valid @RequestBody SignUpRequest signupRequest, BindingResult theBindingResult){
        logger.info("SignUp Admin Request: {}" , signupRequest.toString());
        if(theBindingResult.hasErrors()) {
            throw new ValidationErrorException(theBindingResult.getFieldError().getDefaultMessage());
        }
        authService.signUpAdmin(signupRequest);
        return ResponseEntity.ok(new AuthMessageResponse(HttpStatus.ACCEPTED.value(), "Admin registered successfully", System.currentTimeMillis(), false));
    }


    @PostMapping("/login")
    @Operation(summary = "User Login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest, BindingResult theBindingResult){
        logger.info("Login Request: {}" , loginRequest.toString());
        if(theBindingResult.hasErrors()){
            throw new ValidationErrorException(theBindingResult.getFieldError().getDefaultMessage());
        }
        JwtResponse jwtResponse = authService.userLogin(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }


}
