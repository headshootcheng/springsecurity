package com.luv2code.demo.demo.services;

import com.luv2code.demo.demo.modals.responses.UserInfo;

public interface UserService {
    UserInfo getUserInfoFromUsername(String username);
}
