package com.tkachenko.yevhen.workout.controller;

import com.tkachenko.yevhen.workout.dto.LoginRequestDto;
import com.tkachenko.yevhen.workout.dto.UserDto;
import com.tkachenko.yevhen.workout.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        UserDto userDto = authService.authenticate(loginRequestDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        UserDto registeredUser = authService.register(userDto);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}