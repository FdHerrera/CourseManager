package com.institution.manager.dto.request;

import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class NewCourseDto {

    private String courseName;
    private List<Student> students;
    private Professor professor;

}
