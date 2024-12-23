package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.LoginRequestDto;
import com.tkachenko.yevhen.workout.dto.UserDto;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.UserMapper;
import com.tkachenko.yevhen.workout.repository.UserRepository;
import com.tkachenko.yevhen.workout.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto authenticate(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

        if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPasswordHash())) {
            return UserMapper.mapToUserDto(user);
        } else {
            throw new ResourceNotFoundException("Invalid username or password");
        }
    }

    @Override
    public UserDto register(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }
}