package com.luv2code.demo.demo.convertors;

import com.luv2code.demo.demo.modals.Account;
import com.luv2code.demo.demo.modals.responses.UserInfo;

import java.util.List;
import java.util.stream.Collectors;

public class UserConvertor {
    public static UserInfo fromAccountToUserInfo(Account account){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(account.getUserId());
        userInfo.setUsername(account.getUsername());
        userInfo.setEmail(account.getEmail());
        List<String> roleList = account.getRoleList().stream().map(role -> role.getAuthority().toString()).collect(Collectors.toList());
        userInfo.setRoles(roleList);
        return userInfo;
    }
}
