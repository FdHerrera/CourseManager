package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.entity.User;
import com.institution.manager.exception.CanNotCreateUserException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.service.interf.IAuthService;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    @Autowired
    private final MessageSource messageSource;
    @Autowired
    private final ProjectionFactory projectionFactory;

    public UserResponseDto registerUser(NewUserDto newUserDto, boolean isProfessor) throws CanNotCreateUserException, UserNotFoundException {
        try {
            userService.createUser(newUserDto);
        }catch (CanNotCreateUserException e){
            throw new CanNotCreateUserException(messageSource.getMessage("error.cant.create.user", null, Locale.getDefault()));
        }
        String email = newUserDto.getEmail();
        if(isProfessor)
            userService.setProfessorRole(email);
        else
            userService.setStudentRole(email);

        User newUser = userService.findUser(email);

        return projectionFactory.createProjection(UserResponseDto.class, newUser);
    }

}
