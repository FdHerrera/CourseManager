package com.institution.manager.exception;

public class CanNotSendEmailException extends Exception{
    public CanNotSendEmailException (String message){
        super(message);
    }
}
