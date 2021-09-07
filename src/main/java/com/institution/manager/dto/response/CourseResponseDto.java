package com.institution.manager.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CourseResponseDto {

    private Long id;
    private String courseName;
    private List<StudentResponseDto> students;
    private ProfessorResponseDto professor;

    public CourseResponseDto(Long id, List<StudentResponseDto> students, ProfessorResponseDto professor, String courseName) {
        this.id = id;
        this.students = students;
        this.professor = professor;
        this.courseName = courseName;
    }
}
