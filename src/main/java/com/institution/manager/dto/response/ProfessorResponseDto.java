package com.institution.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface ProfessorResponseDto {

    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getUsername();

}
