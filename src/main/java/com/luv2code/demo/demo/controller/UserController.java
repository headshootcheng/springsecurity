package com.luv2code.demo.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "user test";
    }
}
