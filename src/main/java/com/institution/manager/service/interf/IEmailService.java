package com.institution.manager.service.interf;

import com.institution.manager.exception.CanNotSendEmailException;
import com.institution.manager.exception.UserNotFoundException;

public interface IEmailService {
    void sendEmail(String email) throws UserNotFoundException, CanNotSendEmailException;
}
