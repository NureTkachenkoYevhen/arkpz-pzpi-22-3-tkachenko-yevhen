package com.tkachenko.yevhen.workout.repository;

import com.tkachenko.yevhen.workout.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
