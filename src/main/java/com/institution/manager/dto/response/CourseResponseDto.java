package com.institution.manager.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public interface CourseResponseDto {

    Long getId();
    String getCourseName();
    List<Student> getStudents();
    Professor getProfessor();

}
