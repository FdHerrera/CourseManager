package com.institution.manager.service.interf;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.exception.CanNotCreateUserException;

public interface IUserService {
    UserResponseDto createUser(NewUserDto newUserDto) throws CanNotCreateUserException;
}
