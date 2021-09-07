package com.institution.manager.service.interf;

import com.institution.manager.exception.token.CanNotCreateTokenException;
import com.institution.manager.exception.email.CanNotSendEmailException;
import com.institution.manager.exception.user.UserNotFoundException;

public interface IEmailService {
    void sendEmail(String email) throws UserNotFoundException, CanNotSendEmailException, CanNotCreateTokenException;
    void sendEmail(String email, boolean isProfessor) throws UserNotFoundException, CanNotSendEmailException, CanNotCreateTokenException;
}
