package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.exception.CourseNotFoundException;
import com.institution.manager.exception.UserIsNotAProfessorException;
import com.institution.manager.exception.UserIsNotAStudentException;
import com.institution.manager.exception.UserNotFoundException;


public interface ICourseService {

    CourseResponseDto createCourse(NewCourseDto newCourseDto);

    CourseResponseDto setProfessor(Long courseId, String professorEmail) throws CourseNotFoundException, UserNotFoundException, UserIsNotAProfessorException;

    CourseResponseDto addStudent(Long courseId, String email) throws CourseNotFoundException, UserNotFoundException, UserIsNotAStudentException;

}
