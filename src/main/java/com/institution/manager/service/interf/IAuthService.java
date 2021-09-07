package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.exception.email.CanNotSendEmailException;
import com.institution.manager.exception.token.CanNotCreateTokenException;
import com.institution.manager.exception.token.TokenException;
import com.institution.manager.exception.user.CanNotCreateUserException;
import com.institution.manager.exception.user.UserNotFoundException;

public interface IAuthService {
    UserResponseDto registerUser(NewUserDto newUser) throws CanNotCreateUserException, UserNotFoundException, CanNotSendEmailException, CanNotCreateTokenException;

    UserResponseDto confirmToken(String token) throws UserNotFoundException, TokenException;
}
