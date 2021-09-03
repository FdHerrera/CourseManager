package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;
import com.institution.manager.entity.Course;
import com.institution.manager.repo.CourseRepo;
import com.institution.manager.service.interf.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepo repo;
    @Autowired
    private final ProjectionFactory projectionFactory;

    public CourseResponseDto createCourse(NewCourseDto newCourseDto){
        Course newCourse = new Course(newCourseDto.getCourseName());
        repo.save(newCourse);
        return projectionFactory.createProjection(CourseResponseDto.class, newCourse);
    }

}
