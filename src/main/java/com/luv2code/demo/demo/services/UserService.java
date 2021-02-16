package com.luv2code.demo.demo.services;

import com.luv2code.demo.demo.modals.responses.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> getUserList();

    UserInfo getUserInfoById(Long id);

    UserInfo getUserInfoFromUsername(String username);
}
