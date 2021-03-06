package com.institution.manager.controller;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.exception.course.CourseNotFoundException;
import com.institution.manager.exception.user.UserIsNotAProfessorException;
import com.institution.manager.exception.user.UserIsNotAStudentException;
import com.institution.manager.exception.user.UserNotFoundException;
import com.institution.manager.service.interf.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/course")
@AllArgsConstructor
public class CourseController {

    private final ICourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getCourses(){
        List<CourseResponseDto> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

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

    @PutMapping(path = "/subscribe_to_course")
    public ResponseEntity<CourseResponseDto> subscribeToCourse (@RequestParam("courseId") Long courseId, @RequestParam("studentEmail") String studentEmail) throws CourseNotFoundException, UserNotFoundException, UserIsNotAStudentException {
        CourseResponseDto courseWithNewStudent = courseService.addStudent(courseId, studentEmail);
        return new ResponseEntity<>(courseWithNewStudent, HttpStatus.OK);
    }

}
