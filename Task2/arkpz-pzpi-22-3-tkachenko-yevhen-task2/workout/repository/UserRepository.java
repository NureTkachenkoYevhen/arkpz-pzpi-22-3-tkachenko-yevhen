package com.tkachenko.yevhen.workout.repository;

import com.tkachenko.yevhen.workout.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
