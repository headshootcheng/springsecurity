package com.luv2code.demo.demo.service.impl;

import com.luv2code.demo.demo.enums.ERole;
import com.luv2code.demo.demo.exception.ValidationErrorException;
import com.luv2code.demo.demo.modal.Account;
import com.luv2code.demo.demo.modal.Role;
import com.luv2code.demo.demo.modal.request.LoginRequest;
import com.luv2code.demo.demo.modal.request.SignupRequest;
import com.luv2code.demo.demo.modal.response.JwtResponse;
import com.luv2code.demo.demo.repository.AccountRepository;
import com.luv2code.demo.demo.service.AuthService;
import com.luv2code.demo.demo.service.secure.UserDetailsImpl;
import com.luv2code.demo.demo.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void signUpUser(SignupRequest signupRequest){
        if(accountRepository.existsAccountByUsername(signupRequest.getUsername())){
            throw new ValidationErrorException("Error: Username is already taken ");
        }
        if(accountRepository.existsAccountByEmail(signupRequest.getEmail())){
            throw new ValidationErrorException("Error: Email is already taken ");
        }
        Account account = new Account(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(account, ERole.ROLE_USER));
        account.setRoleList(roleList);
        accountRepository.save(account);
    }

    @Override
    public void signUpAdmin(SignupRequest signupRequest){
        if(accountRepository.existsAccountByUsername(signupRequest.getUsername())){
            throw new ValidationErrorException("Error: Username is already taken ");
        }
        if(accountRepository.existsAccountByEmail(signupRequest.getEmail())){
            throw new ValidationErrorException("Error: Email is already taken ");
        }
        Account account = new Account(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role(account, ERole.ROLE_USER));
        roleList.add(new Role(account, ERole.ROLE_ADMIN));
        account.setRoleList(roleList);
        accountRepository.save(account);
    }


    @Override
    public JwtResponse userLogin(LoginRequest loginRequest){
        logger.info("user Login");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }


}
