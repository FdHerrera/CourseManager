package com.institution.manager.exception.token;

import com.institution.manager.exception.token.TokenException;

public class CanNotCreateTokenException extends TokenException {
    public CanNotCreateTokenException(String message){
        super(message);
    }
}
