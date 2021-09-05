package com.institution.manager.util;

import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.dto.response.ProfessorResponseDto;
import com.institution.manager.dto.response.StudentResponseDto;
import com.institution.manager.entity.Course;
import com.institution.manager.entity.Student;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CourseProjector {

    @Autowired
    private final ProjectionFactory projectionFactory;

    public CourseResponseDto createProjection(Course course){
        List<Student> students = course.getStudents();
        List<StudentResponseDto> studentsList = new ArrayList<>();
        ProfessorResponseDto professor = null;
        if(students != null) {
            for (Student student : students) {
                StudentResponseDto currentStudent = projectionFactory.createProjection(StudentResponseDto.class, student);
                studentsList.add(currentStudent);
            }
        }
        if (course.getProfessor() != null){
            professor = projectionFactory.createProjection(ProfessorResponseDto.class, course.getProfessor());
        }
        return new CourseResponseDto(
                course.getId(), studentsList, professor
        );
    }

}
