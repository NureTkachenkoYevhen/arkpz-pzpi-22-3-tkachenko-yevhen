package com.tkachenko.yevhen.workout.dto;


import java.time.LocalDateTime;

public class UserDto {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();

    public UserDto() {
    }

    public UserDto(Long userId, String username, String email, String password, LocalDateTime createdAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
