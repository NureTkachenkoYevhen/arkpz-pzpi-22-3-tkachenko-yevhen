package com.tkachenko.yevhen.workout.mapper;

import com.tkachenko.yevhen.workout.dto.UserDto;
import com.tkachenko.yevhen.workout.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                null,
                user.getCreatedAt()
        );
    }

    public static User mapToUser(UserDto userDto) {
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getEmail(),
                null,
                userDto.getCreatedAt()
        );
    }
}
