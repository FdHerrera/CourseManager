package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.entity.User;
import com.institution.manager.enumerate.ERole;
import com.institution.manager.exception.CanNotCreateUserException;
import com.institution.manager.repo.UsersRepo;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UsersRepo repo;
    @Autowired
    private final ProjectionFactory projectionFactory;
    @Autowired
    private final BCryptPasswordEncoder encoder;

    public UserResponseDto createUser(NewUserDto newUserDto) throws CanNotCreateUserException {
        HashSet<ERole> roles = new HashSet<>();
        String encodedPass = encoder.encode(newUserDto.getPassword());
        User newUser = new User(newUserDto.getFirstName(),
                newUserDto.getLastName(),
                newUserDto.getEmail(),
                newUserDto.getDni(),
                encodedPass,
                newUserDto.getPhoneNumber(),
                Date.from(Instant.now()),
                roles);
        return projectionFactory.createProjection(UserResponseDto.class, repo.save(newUser));
    }

}
