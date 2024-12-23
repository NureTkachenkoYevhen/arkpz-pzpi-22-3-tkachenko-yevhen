package com.tkachenko.yevhen.workout.service;

import com.tkachenko.yevhen.workout.dto.LoginRequestDto;
import com.tkachenko.yevhen.workout.dto.UserDto;

public interface AuthService {
    UserDto authenticate(LoginRequestDto loginRequestDto);
    UserDto register(UserDto userDto);
}