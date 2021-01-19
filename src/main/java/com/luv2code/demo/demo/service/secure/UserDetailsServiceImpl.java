package com.luv2code.demo.demo.service.secure;

import com.luv2code.demo.demo.modal.Account;
import com.luv2code.demo.demo.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("load User by Username: " + username);
        Account account = accountRepository.findAccountByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found !!!"));
        return UserDetailsImpl.build(account);
    }
}
