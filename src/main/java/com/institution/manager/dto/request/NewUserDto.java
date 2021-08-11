package com.institution.manager.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class NewUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String password;
    private String phoneNumber;

}
