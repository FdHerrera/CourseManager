package com.institution.manager.controller;

import com.institution.manager.dto.request.NewUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody NewUserDto newUserDto){
        return null;
    }

}
