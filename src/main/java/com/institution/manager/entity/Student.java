package com.institution.manager.entity;

import com.institution.manager.enumerate.ERole;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Student extends User implements UserDetails {

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
    @NotBlank(message = "Valid email is required")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "DNI is required")
    private String dni;
    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    private String password;
    @Column(nullable = false, name = "phone_number")
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
    public Student(String firstName, String lastName, String email, String dni, String password, String phoneNumber, Collection<? extends GrantedAuthority> authorities) {
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

    public static Student build(Student student){
        return new Student(student.getFirstName(), student.getLastName(), student.getEmail(), student.getDni(), student.getPassword(), student.getPhoneNumber(), student.getAuthorities());
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
