package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.entity.Course;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;
import com.institution.manager.entity.User;
import com.institution.manager.exception.course.CourseNotFoundException;
import com.institution.manager.exception.user.UserIsNotAProfessorException;
import com.institution.manager.exception.user.UserIsNotAStudentException;
import com.institution.manager.exception.user.UserNotFoundException;
import com.institution.manager.repo.CourseRepo;
import com.institution.manager.service.interf.ICourseService;
import com.institution.manager.service.interf.IUserService;
import com.institution.manager.util.CourseProjector;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepo repo;
    private final IUserService userService;
    private final CourseProjector courseProjector;
    @Autowired
    private final MessageSource messageSource;

    @Override
    public List<CourseResponseDto> getCourses(){
        List<Course> courses = repo.findAll();
        List<CourseResponseDto> courseResponseDtoS = new ArrayList<>();
        for (Course course : courses){
            CourseResponseDto projectedCourse = courseProjector.createProjection(course);
            courseResponseDtoS.add(projectedCourse);
        }
        return courseResponseDtoS;
    }

    @Override
    public CourseResponseDto createCourse(NewCourseDto newCourseDto) {
        Course newCourse = new Course(newCourseDto.getCourseName());
        repo.save(newCourse);
        return courseProjector.createProjection(newCourse);
    }

    @Override
    public CourseResponseDto setProfessor(Long courseId, String professorEmail) throws CourseNotFoundException, UserNotFoundException, UserIsNotAProfessorException {
        Course courseFound = findCourse(courseId);
        User userFound = userService.findUser(professorEmail);
        userService.checkIfIsProfessor(userFound);
        courseFound.setProfessor((Professor) userFound);
        Course courseWithProfessor = repo.save(courseFound);
        return courseProjector.createProjection(courseWithProfessor);
    }

    @Override
    public CourseResponseDto addStudent(Long courseId, String email) throws CourseNotFoundException, UserNotFoundException, UserIsNotAStudentException {
        Course courseFound = findCourse(courseId);
            User studentFound =userService.findUser(email);
            userService.checkIfIsStudent(studentFound);
            List<Student> students = courseFound.getStudents();
            students.add((Student) studentFound);
            courseFound.setStudents(students);
            return courseProjector.createProjection(courseFound);

    }

    private Course findCourse(Long courseId) throws CourseNotFoundException {
        return repo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                        messageSource.getMessage("error.course.not.found", null, Locale.getDefault()))
                );
    }

}
