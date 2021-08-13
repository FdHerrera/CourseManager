package com.institution.manager.dto.request;

import lombok.Data;

@Data
public class NewUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String password;
    private String phoneNumber;
    private Boolean isProfessor;

}
