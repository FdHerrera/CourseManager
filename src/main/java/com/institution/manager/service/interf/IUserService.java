package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;
import com.institution.manager.entity.User;
import com.institution.manager.exception.UserNotFoundException;

public interface IUserService {

    Professor createProfessor(NewUserDto email);

    Student createStudent(NewUserDto email);

    User findUser(String email) throws UserNotFoundException;

}
