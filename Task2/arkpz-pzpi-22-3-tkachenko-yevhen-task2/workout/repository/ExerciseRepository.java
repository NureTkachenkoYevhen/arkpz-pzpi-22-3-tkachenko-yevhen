package com.tkachenko.yevhen.workout.repository;

import com.tkachenko.yevhen.workout.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
