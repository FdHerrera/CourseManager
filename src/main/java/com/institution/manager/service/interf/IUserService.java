package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;
import com.institution.manager.entity.User;
import com.institution.manager.exception.user.UserIsNotAProfessorException;
import com.institution.manager.exception.user.UserIsNotAStudentException;
import com.institution.manager.exception.user.UserNotFoundException;

public interface IUserService {

    Professor createProfessor(NewUserDto email);

    Student createStudent(NewUserDto email);

    User findUser(String email) throws UserNotFoundException;

    User findUser(Long id) throws UserNotFoundException;

    void checkIfIsProfessor(User user) throws UserIsNotAProfessorException;

    boolean checkIfExists(String email) throws UserNotFoundException;

    void checkIfIsStudent(User user) throws UserIsNotAStudentException;

    void enableProfessor(Long id);

    void enableStudent(Long id);
}
