package com.tkachenko.yevhen.workout.controller;

import com.tkachenko.yevhen.workout.dto.UserDto;
import com.tkachenko.yevhen.workout.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UserDto updatedDto) {
        UserDto userDto = userService.updateUser(userId, updatedDto);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User deleted successfully.",HttpStatus.OK);
    }
}