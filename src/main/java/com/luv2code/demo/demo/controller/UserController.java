package com.luv2code.demo.demo.controller;

import com.luv2code.demo.demo.modal.response.UserInfo;
import com.luv2code.demo.demo.service.UserService;
import com.luv2code.demo.demo.util.JwtUtils;
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
