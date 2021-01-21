package com.luv2code.demo.demo.service;

import com.luv2code.demo.demo.modal.response.UserInfo;

public interface UserService {
    UserInfo getUserInfoFromUsername(String username);
}
