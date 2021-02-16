package com.luv2code.demo.demo.services.impl;

import com.luv2code.demo.demo.convertors.UserConvertor;
import com.luv2code.demo.demo.exceptions.ValidationErrorException;
import com.luv2code.demo.demo.modals.Account;
import com.luv2code.demo.demo.modals.Role;
import com.luv2code.demo.demo.modals.responses.UserInfo;
import com.luv2code.demo.demo.repositories.AccountRepository;
import com.luv2code.demo.demo.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<UserInfo> getUserList(){
        List<Account> accountList = accountRepository.findAll();
        List<UserInfo> userInfoList = accountList.stream().map(account -> UserConvertor.fromAccountToUserInfo(account)).collect(Collectors.toList());
        return userInfoList;
    }

    @Override
    public UserInfo getUserInfoById(Long id){
        Account account = accountRepository.findById(id).orElse(null);
        UserInfo userInfo = UserConvertor.fromAccountToUserInfo(account);
        return userInfo;
    }

    @Override
    public UserInfo getUserInfoFromUsername(String username){
        Account account = accountRepository.findAccountByUsername(username).orElseThrow(() -> new ValidationErrorException("User is not found !!!"));
        UserInfo userInfo = UserConvertor.fromAccountToUserInfo(account);
        return userInfo;
    }
}
