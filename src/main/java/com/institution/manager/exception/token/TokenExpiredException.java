package com.institution.manager.exception.token;

import com.institution.manager.exception.token.TokenException;

public class TokenExpiredException extends TokenException {
    public TokenExpiredException(String message){
        super(message);
    }
}
