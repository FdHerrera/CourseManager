package com.institution.manager.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Column(nullable = false)
    @NotBlank(message = "Valid email is required")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "DNI is required")
    private String dni;
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;
    @Column(nullable = false)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private Boolean enabled = Boolean.FALSE;
    private Boolean deleted = Boolean.FALSE;
    @ElementCollection(targetClass=GrantedAuthority.class)
    private Collection<? extends GrantedAuthority> authorities;

    public User(String firstName, String lastName, String email, String dni, String password, String phoneNumber, Date createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
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
