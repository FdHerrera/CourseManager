package com.institution.manager.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Data
public class User implements UserDetails {

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String dni;
    protected String password;
    protected String phoneNumber;
    protected Date createdAt;
    protected boolean enabled;
    protected Collection<? extends GrantedAuthority> authorities;

    public User() {
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
