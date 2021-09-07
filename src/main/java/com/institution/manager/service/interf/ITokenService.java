package com.institution.manager.service.interf;

import com.institution.manager.entity.ConfirmationToken;
import com.institution.manager.exception.token.CanNotCreateTokenException;
import com.institution.manager.exception.token.TokenException;
import com.institution.manager.exception.token.TokenNotFoundException;
import com.institution.manager.exception.user.UserNotFoundException;

public interface ITokenService {
    String createToken(String email) throws UserNotFoundException, CanNotCreateTokenException;

    ConfirmationToken getToken(String token) throws TokenNotFoundException;

    void confirmToken(String token) throws TokenException;
}
