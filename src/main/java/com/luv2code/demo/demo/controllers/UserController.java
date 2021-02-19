package com.luv2code.demo.demo.controllers;

import com.luv2code.demo.demo.modals.responses.UserInfo;
import com.luv2code.demo.demo.services.UserService;
import com.luv2code.demo.demo.utils.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@RestController
@RequestMapping("/api/user")
@Tag(name = "UserController" , description = "the User API")
public class UserController implements IStandardApi{

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    @Operation(
        summary = "Get all information of users",
        responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
        }
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<UserInfo> getAllUser(){
        List<UserInfo> userInfoList  = userService.getUserList();
        for (UserInfo userInfo : userInfoList) {
            Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserInfoById(userInfo.getId())).withRel("Self");
            userInfo.add(selfLink);
        }
        return userInfoList;
    }

    @GetMapping("/user/{id}")
    @Operation(
        summary = "Get user By Id",
        responses = {
                @ApiResponse(responseCode = "200", description = "Success"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "400", description = "Bad Request")
        }
    )
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public UserInfo getUserInfoById(@PathVariable Long id){
        UserInfo userInfo = userService.getUserInfoById(id);
        Link allLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser()).withRel("All");
        userInfo.add(allLink);
        return userInfo;
    }

    @GetMapping("/userInfo")
    @Operation(
        summary = "Get user info based on token",
        responses = {
                @ApiResponse(responseCode = "200", description = "Success"),
                @ApiResponse(responseCode = "401", description = "Unauthorized"),
                @ApiResponse(responseCode = "400", description = "Bad Request")
        }
    )

    public UserInfo getUserInfo(HttpServletRequest httpServletRequest){
        String token = jwtUtils.parseJwt(httpServletRequest);
        String username = jwtUtils.getUserNameFromJwtToken(token);
        UserInfo userInfo = userService.getUserInfoFromUsername(username);
        return userInfo;
    }
}
