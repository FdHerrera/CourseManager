package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.entity.ConfirmationToken;
import com.institution.manager.entity.User;
import com.institution.manager.exception.email.CanNotSendEmailException;
import com.institution.manager.exception.token.CanNotCreateTokenException;
import com.institution.manager.exception.token.TokenConfirmedException;
import com.institution.manager.exception.token.TokenException;
import com.institution.manager.exception.token.TokenExpiredException;
import com.institution.manager.exception.user.CanNotCreateUserException;
import com.institution.manager.exception.user.UserNotFoundException;
import com.institution.manager.service.interf.IAuthService;
import com.institution.manager.service.interf.IEmailService;
import com.institution.manager.service.interf.ITokenService;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IUserService userService;
    private final IEmailService emailService;
    private final ITokenService tokenService;
    @Autowired
    private final ProjectionFactory projectionFactory;
    @Autowired
    private final MessageSource messageSource;

    public UserResponseDto registerUser(NewUserDto newUserDto) throws CanNotCreateUserException, UserNotFoundException, CanNotSendEmailException, CanNotCreateTokenException {
        boolean isProfessor = newUserDto.getIsProfessor();
        String email = newUserDto.getEmail();
        if (userService.checkIfExists(email))
            throw new CanNotCreateUserException(messageSource.getMessage("error.email.is.already.registered", null, Locale.getDefault()));
        User newUser;
        if (isProfessor) {
            newUser = userService.createProfessor(newUserDto);
            emailService.sendEmail(email, true);
        }
        else {
            newUser = userService.createStudent(newUserDto);
            emailService.sendEmail(email);
        }
        return projectionFactory.createProjection(UserResponseDto.class, newUser);
    }

    @Override
    @Transactional
    public UserResponseDto confirmToken(String token) throws UserNotFoundException, TokenException {
        ConfirmationToken confirmationToken = tokenService.getToken(token);
        boolean tokenIsConfirmed = confirmationToken.getConfirmedAt() != null;
        boolean tokenIsExpired = confirmationToken.getExpiresAt().before(Date.from(Instant.now()));
        if(tokenIsExpired)
            throw new TokenExpiredException(messageSource.getMessage("error.token.expired", null, Locale.getDefault()));
        if(!tokenIsConfirmed)
            tokenService.confirmToken(confirmationToken.getToken());
        else
            throw new TokenConfirmedException(messageSource.getMessage("error.token.confirmed", null, Locale.getDefault()));
        String email = null;
        if (confirmationToken.getProfessor()!=null){
            Long id =confirmationToken.getProfessor().getId();
            email = confirmationToken.getProfessor().getEmail();
            userService.enableProfessor(id);
        }
        else if(confirmationToken.getStudent()!=null){
            Long id = confirmationToken.getStudent().getId();
            email = confirmationToken.getStudent().getEmail();
            userService.enableStudent(id);
        }
        User user = userService.findUser(email);
        return projectionFactory.createProjection(UserResponseDto.class, user);
    }

}
