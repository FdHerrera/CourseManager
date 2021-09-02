package com.institution.manager.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.ElementCollection;
import java.util.Collection;
import java.util.Date;

@Data
public class User{

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String dni;
    protected String password;
    protected String phoneNumber;
    protected Date createdAt;
    @ElementCollection(targetClass= GrantedAuthority.class)
    protected Collection<? extends GrantedAuthority> authorities;

    public User() {
    }


}
