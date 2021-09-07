package com.institution.manager.exception.email;

public class CanNotSendEmailException extends Exception{
    public CanNotSendEmailException (String message){
        super(message);
    }
}
