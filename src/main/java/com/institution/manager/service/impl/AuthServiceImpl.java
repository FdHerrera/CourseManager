package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.entity.User;
import com.institution.manager.exception.CanNotCreateTokenException;
import com.institution.manager.exception.CanNotCreateUserException;
import com.institution.manager.exception.CanNotSendEmailException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.service.interf.IAuthService;
import com.institution.manager.service.interf.IEmailService;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final IEmailService emailService;
    @Autowired
    private final ProjectionFactory projectionFactory;

    public UserResponseDto registerUser(NewUserDto newUserDto) throws CanNotCreateUserException, UserNotFoundException, CanNotSendEmailException, CanNotCreateTokenException {
        boolean isProfessor = newUserDto.getIsProfessor();
        String email = newUserDto.getEmail();
        User newUser;
        if (isProfessor) {
            newUser = userService.createProfessor(newUserDto);
            //emailService.sendEmail(email, true);
        }
        else {
            newUser = userService.createStudent(newUserDto);
            //emailService.sendEmail(email);
        }
        return projectionFactory.createProjection(UserResponseDto.class, newUser);
    }

}
