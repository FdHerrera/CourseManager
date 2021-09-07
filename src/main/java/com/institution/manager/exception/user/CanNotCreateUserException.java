package com.institution.manager.exception.user;

import com.institution.manager.exception.user.UserException;

public class CanNotCreateUserException extends UserException {
    public CanNotCreateUserException(String message){
        super(message);
    }
}
