package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewCourseDto;
import com.institution.manager.dto.response.CourseResponseDto;

public interface ICourseService {

    CourseResponseDto createCourse(NewCourseDto newCourseDto);

}
