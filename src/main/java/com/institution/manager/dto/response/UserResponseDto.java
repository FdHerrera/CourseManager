package com.institution.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface UserResponseDto {

    Long getId();
    String getFirstName();
    String getLastName();
    String getDni();
    String getPhoneNumber();

}
