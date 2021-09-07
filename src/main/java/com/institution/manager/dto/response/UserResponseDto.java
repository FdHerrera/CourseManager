package com.institution.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface UserResponseDto {

    Long getId();
    String getFirstName();
    String getLastName();
    String getDni();
    String getPhoneNumber();
    String getUsername();
    Collection<? extends GrantedAuthority> getAuthorities();
    boolean getEnabled();

}
