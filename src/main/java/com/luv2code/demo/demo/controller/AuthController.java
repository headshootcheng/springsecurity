package com.luv2code.demo.demo.controller;

import com.luv2code.demo.demo.exception.ValidationErrorException;
import com.luv2code.demo.demo.modal.request.LoginRequest;
import com.luv2code.demo.demo.modal.request.SignupRequest;
import com.luv2code.demo.demo.modal.response.AuthMessageResponse;
import com.luv2code.demo.demo.modal.response.JwtResponse;
import com.luv2code.demo.demo.service.AuthService;
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


//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/preauthorize")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String preauthorize(){
        return "preauthorize";
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthMessageResponse> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult theBindingResult){
        logger.info("SignUp Request: "+ signupRequest.toString());
        if(theBindingResult.hasErrors()){
            throw new ValidationErrorException(theBindingResult.getFieldError().getDefaultMessage());
        }
        authService.signUpUser(signupRequest);
        return ResponseEntity.ok(new AuthMessageResponse(HttpStatus.ACCEPTED.value(), "User registered successfully", System.currentTimeMillis(), false));
    }

    @PostMapping("/signupAdmin")
    public ResponseEntity<AuthMessageResponse> registerAdmin(@Valid @RequestBody SignupRequest signupRequest, BindingResult theBindingResult){
        logger.info("SignUp Request: "+ signupRequest.toString());
        if(theBindingResult.hasErrors()){
            throw new ValidationErrorException(theBindingResult.getFieldError().getDefaultMessage());
        }
        authService.signUpAdmin(signupRequest);
        return ResponseEntity.ok(new AuthMessageResponse(HttpStatus.ACCEPTED.value(), "Admin registered successfully", System.currentTimeMillis(), false));
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> userLogin(@Valid @RequestBody LoginRequest loginRequest, BindingResult theBindingResult){
        logger.info("Login Request: " + loginRequest.toString());
        if(theBindingResult.hasErrors()){
            throw new ValidationErrorException(theBindingResult.getFieldError().getDefaultMessage());
        }
        JwtResponse jwtResponse = authService.userLogin(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }


}
