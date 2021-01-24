package com.luv2code.demo.demo.controllers;

import com.luv2code.demo.demo.modals.responses.UserInfo;
import com.luv2code.demo.demo.services.UserService;
import com.luv2code.demo.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public UserInfo getUserInfo(HttpServletRequest httpServletRequest){
        String token = jwtUtils.parseJwt(httpServletRequest);
        String username = jwtUtils.getUserNameFromJwtToken(token);
        UserInfo userInfo = userService.getUserInfoFromUsername(username);
        return userInfo;
    }
}
