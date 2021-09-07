package com.institution.manager.exception.token;

import com.institution.manager.exception.token.TokenException;

public class TokenConfirmedException extends TokenException {
    public TokenConfirmedException(String message){
        super(message);
    }
}
