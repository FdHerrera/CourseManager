package com.institution.manager.service.impl;

import com.institution.manager.entity.ConfirmationToken;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;
import com.institution.manager.entity.User;
import com.institution.manager.exception.CanNotCreateTokenException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.repo.ConfirmationTokenRepo;
import com.institution.manager.service.interf.ITokenService;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements ITokenService {

    private final ConfirmationTokenRepo repo;
    private final IUserService userService;
    @Autowired
    private final MessageSource messageSource;

    @Override
    public String createToken(String email) throws UserNotFoundException, CanNotCreateTokenException {
        String token = UUID.randomUUID().toString();
        User user = userService.findUser(email);
        if(user instanceof Student) {
            ConfirmationToken newToken = new ConfirmationToken(
                    token,
                    Date.from(Instant.now()),
                    Date.from(Instant.now().plusSeconds(900)),
                    (Student) user);
            repo.save(newToken);
        } else if (user instanceof Professor){
            ConfirmationToken newToken = new ConfirmationToken(
                    token,
                    Date.from(Instant.now()),
                    Date.from(Instant.now().plusSeconds(900)),
                    (Professor) user);
            repo.save(newToken);
        }
        else
            throw new CanNotCreateTokenException(messageSource.getMessage("error.cant.create.token", null, Locale.getDefault()));
        return token;
    }
}
