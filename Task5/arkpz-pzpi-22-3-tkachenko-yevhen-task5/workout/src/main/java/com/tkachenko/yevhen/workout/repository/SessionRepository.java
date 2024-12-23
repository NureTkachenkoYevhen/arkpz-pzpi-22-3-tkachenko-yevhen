package com.tkachenko.yevhen.workout.repository;

import com.tkachenko.yevhen.workout.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUserUserId(Long userId);
}
