package com.institution.manager.controller;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.exception.CourseNotFoundException;
import com.institution.manager.exception.UserIsNotAProfessorException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.service.interf.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @PostMapping(path = "/create_course")
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody NewCourseDto newCourse){
        CourseResponseDto courseCreated = courseService.createCourse(newCourse);
        return  new ResponseEntity<>(courseCreated, HttpStatus.CREATED);
    }

    @PutMapping(path = "/set_professor")
    public ResponseEntity<CourseResponseDto> setProfessorToCourse(@RequestParam("courseId") Long courseId,@RequestParam("professorEmail")  String professorEmail) throws CourseNotFoundException, UserNotFoundException, UserIsNotAProfessorException {
        CourseResponseDto courseWithProfessor = courseService.setProfessor(courseId, professorEmail);
        return new ResponseEntity<>(courseWithProfessor, HttpStatus.OK);
    }

}
