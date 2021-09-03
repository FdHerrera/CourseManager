package com.institution.manager.entity;

import com.institution.manager.enumerate.ERole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor extends User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "DNI is required")
    private String dni;
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;
    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    private Boolean enabled = Boolean.FALSE;
    private Boolean deleted = Boolean.FALSE;
    @ElementCollection(targetClass= GrantedAuthority.class)
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public Professor(String firstName, String lastName, String email, String dni, String password, String phoneNumber, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.createdAt = new Date();
        this.authorities = authorities;
    }

    public static Professor build(Professor professor){
        return new Professor(professor.getFirstName(), professor.getLastName(), professor.getEmail(), professor.getDni(), professor.getPassword(), professor.getPhoneNumber(), professor.getAuthorities());
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
