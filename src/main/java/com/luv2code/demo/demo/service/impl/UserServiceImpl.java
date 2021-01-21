package com.luv2code.demo.demo.service.impl;

import com.luv2code.demo.demo.exception.ValidationErrorException;
import com.luv2code.demo.demo.modal.Account;
import com.luv2code.demo.demo.modal.Role;
import com.luv2code.demo.demo.modal.response.UserInfo;
import com.luv2code.demo.demo.repository.AccountRepository;
import com.luv2code.demo.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserInfo getUserInfoFromUsername(String username){
        Account account = accountRepository.findAccountByUsername(username).orElseThrow(() -> new ValidationErrorException("User is not found !!!"));
        List<Role> roleList = account.getRoleList();
        List<String> roleListStr = roleList.stream().map(role-> role.getAuthority().name()).collect(Collectors.toList());
        UserInfo userInfo = new UserInfo(account.getUsername(), account.getEmail(), roleListStr);
        return userInfo;
    }
}
