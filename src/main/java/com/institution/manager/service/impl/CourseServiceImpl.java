package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.entity.Course;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.User;
import com.institution.manager.exception.CourseNotFoundException;
import com.institution.manager.exception.UserIsNotAProfessorException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.repo.CourseRepo;
import com.institution.manager.service.interf.ICourseService;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Map;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepo repo;
    private final IUserService userService;
    @Autowired
    private final ProjectionFactory projectionFactory;
    @Autowired
    private final MessageSource messageSource;

    public CourseResponseDto createCourse(NewCourseDto newCourseDto){
        Course newCourse = new Course(newCourseDto.getCourseName());
        repo.save(newCourse);
        return projectionFactory.createProjection(CourseResponseDto.class, newCourse);
    }

    @Override
    public Map<CourseResponseDto, String> setProfessor(Long courseId, String professorEmail) throws CourseNotFoundException, UserNotFoundException, UserIsNotAProfessorException {
        Course courseFound = repo.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(
                        messageSource.getMessage("error.course.not.found", null, Locale.getDefault()))
                );
        User userFound = userService.findUser(professorEmail);
        try{
            boolean isProfessor = userService.checkIfIsProfessor(userFound);
            if(isProfessor) {
                courseFound.setProfessor((Professor) userFound);
            }
        } catch (UserIsNotAProfessorException e){
            throw new UserIsNotAProfessorException(e.getMessage());
        }
        Course courseWithProfessor = repo.save(courseFound);
        CourseResponseDto courseProjection = projectionFactory.createProjection(CourseResponseDto.class, courseWithProfessor);
        return Map.of(courseProjection, userFound.toString());
    }

}
