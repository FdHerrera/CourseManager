package com.institution.manager.controller;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.dto.response.UserResponseDto;
import com.institution.manager.exception.email.CanNotSendEmailException;
import com.institution.manager.exception.token.CanNotCreateTokenException;
import com.institution.manager.exception.token.TokenException;
import com.institution.manager.exception.user.CanNotCreateUserException;
import com.institution.manager.exception.user.UserNotFoundException;
import com.institution.manager.service.interf.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor
public class AuthController {

    private final IAuthService service;

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody NewUserDto newUserDto) throws CanNotCreateUserException, UserNotFoundException, CanNotSendEmailException, CanNotCreateTokenException {
        UserResponseDto newUser = service.registerUser(newUserDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(path = "/confirm_token")
    public ResponseEntity<UserResponseDto> confirmToken(@RequestParam("ConfirmationToken") String token) throws UserNotFoundException, TokenException {
        UserResponseDto userEnabled = service.confirmToken(token);
        return new ResponseEntity<>(userEnabled, HttpStatus.OK);
    }

}
