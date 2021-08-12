package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.exception.CanNotCreateUserException;
import com.institution.manager.exception.CanNotSendEmailException;
import com.institution.manager.exception.UserNotFoundException;

public interface IAuthService {
    UserResponseDto registerUser(NewUserDto newUser, boolean isProfesor) throws CanNotCreateUserException, UserNotFoundException, CanNotSendEmailException;
}
