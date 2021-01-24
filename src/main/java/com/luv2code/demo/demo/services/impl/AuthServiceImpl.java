package com.luv2code.demo.demo.services.impl;

import com.luv2code.demo.demo.enums.RoleEnum;
import com.luv2code.demo.demo.exceptions.ValidationErrorException;
import com.luv2code.demo.demo.modals.Account;
import com.luv2code.demo.demo.modals.Role;
import com.luv2code.demo.demo.modals.requests.LoginRequest;
import com.luv2code.demo.demo.modals.requests.SignUpRequest;
import com.luv2code.demo.demo.modals.responses.JwtResponse;
import com.luv2code.demo.demo.modals.responses.UserInfo;
import com.luv2code.demo.demo.repositories.AccountRepository;
import com.luv2code.demo.demo.services.AuthService;
import com.luv2code.demo.demo.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public void signUpUser(SignUpRequest signupRequest){
        if(accountRepository.existsAccountByUsername(signupRequest.getUsername())){
            throw new ValidationErrorException("Error: Username is already taken ");
        }
        if(accountRepository.existsAccountByEmail(signupRequest.getEmail())){
            throw new ValidationErrorException("Error: Email is already taken ");
        }
        Account account = new Account(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        account.setRoleList(Collections.singletonList(new Role(account, RoleEnum.ROLE_USER)));
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void signUpAdmin(SignUpRequest signupRequest){
        if(accountRepository.existsAccountByUsername(signupRequest.getUsername())){
            throw new ValidationErrorException("Error: Username is already taken ");
        }
        if(accountRepository.existsAccountByEmail(signupRequest.getEmail())){
            throw new ValidationErrorException("Error: Email is already taken ");
        }
        Account account = new Account(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(account, RoleEnum.ROLE_USER));
        roleList.add(new Role(account, RoleEnum.ROLE_ADMIN));
        account.setRoleList(roleList);
        accountRepository.save(account);
    }


    @Override
    @Transactional
    public JwtResponse userLogin(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        UserInfo userInfo = new UserInfo(userDetails.getUsername(), userDetails.getEmail(), roles);

        return new JwtResponse(jwt, userInfo);
    }


}
