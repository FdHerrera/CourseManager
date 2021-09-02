package com.institution.manager.service.interf;

import com.institution.manager.exception.CanNotCreateTokenException;
import com.institution.manager.exception.UserNotFoundException;

public interface ITokenService {
    String createToken(String email) throws UserNotFoundException, CanNotCreateTokenException;
}
