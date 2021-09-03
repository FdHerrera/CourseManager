package com.institution.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface CourseResponseDto {

    Long getId();
    String getCourseName();

}
