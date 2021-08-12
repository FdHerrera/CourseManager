package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.entity.User;
import com.institution.manager.enumerate.ERole;
import com.institution.manager.exception.CanNotCreateUserException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.repo.UsersRepo;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UsersRepo repo;
    @Autowired
    private final ProjectionFactory projectionFactory;
    @Autowired
    private final BCryptPasswordEncoder encoder;
    @Autowired
    private final MessageSource messageSource;

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

    @Override
    public void setProfessorRole(String email) throws UserNotFoundException {
        User foundUser = repo.findByEmail(email).orElseThrow(
                ()-> new UserNotFoundException(messageSource.getMessage("error.cant.found.user", null, Locale.getDefault()))
        );
        foundUser.getRoles().add(ERole.ROLE_PROFESSOR);
        Set<ERole> roles = foundUser.getRoles();
        SimpleGrantedAuthority professorAuthority = new SimpleGrantedAuthority(roles.stream().filter(eRole -> eRole.equals(ERole.ROLE_PROFESSOR)).findFirst().get().name());
        foundUser.setAuthorities(Collections.singleton(professorAuthority));
    }

}
