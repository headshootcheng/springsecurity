package com.luv2code.demo.demo.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.luv2code.demo.demo.modals.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private List<? extends GrantedAuthority> authorities = new ArrayList<>();

    public UserDetailsImpl(String username, String email, String password, List<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build (Account account){
        List<GrantedAuthority> authorities = account.getRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getAuthority().name())).collect(Collectors.toList());
        return new UserDetailsImpl(
                account.getUsername(),
                account.getEmail(),
                account.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) obj;
        return Objects.equals(username, user.username);
    }
}
