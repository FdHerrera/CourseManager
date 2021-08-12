package com.institution.manager.service.impl;

import com.institution.manager.entity.ConfirmationToken;
import com.institution.manager.entity.User;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.repo.ConfirmationTokenRepo;
import com.institution.manager.service.interf.ITokenService;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenServiceImpl implements ITokenService {

    private final ConfirmationTokenRepo repo;
    private final IUserService userService;

    @Override
    public String createToken(String email) throws UserNotFoundException {
        String token = UUID.randomUUID().toString();
        User user = userService.findUser(email);
        ConfirmationToken newToken = new ConfirmationToken(
                token,
                Date.from(Instant.now()),
                Date.from(Instant.now().plusSeconds(900)),
                user);
        repo.save(newToken);
        return token;
    }
}
