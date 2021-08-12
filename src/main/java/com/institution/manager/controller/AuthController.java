package com.institution.manager.controller;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.exception.CanNotCreateUserException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.service.interf.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthService service;

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody @Valid NewUserDto newUserDto, @RequestParam boolean isProfessor) throws CanNotCreateUserException, UserNotFoundException {
        UserResponseDto newUser = service.registerUser(newUserDto, isProfessor);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
