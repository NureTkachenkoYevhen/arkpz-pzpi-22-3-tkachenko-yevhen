package com.tkachenko.yevhen.workout.service;

import com.tkachenko.yevhen.workout.dto.ExerciseDto;
import com.tkachenko.yevhen.workout.dto.UserDto;

import java.util.List;

public interface ExerciseService {
    ExerciseDto createExercise(ExerciseDto exerciseDto);

    ExerciseDto getExerciseById(Long exerciseId);

    List<ExerciseDto> getAllExercises();

    ExerciseDto updateExercise(Long exerciseId, ExerciseDto updatedExerciseDto);

    void deleteExercise(Long exerciseDto);
}
