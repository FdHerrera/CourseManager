package com.institution.manager.dto.response;

import com.institution.manager.enumerate.ERole;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {
    public String firstName;
    public String lastName;
    public String dni;
    public String phoneNumber;
    public Set<ERole> roles;
}
