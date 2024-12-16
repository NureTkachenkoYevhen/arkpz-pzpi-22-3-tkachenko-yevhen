package com.tkachenko.yevhen.workout.mapper;

import com.tkachenko.yevhen.workout.dto.ExerciseDto;
import com.tkachenko.yevhen.workout.entity.Exercise;

public class ExerciseMapper {
    public static ExerciseDto mapToExerciseDto(Exercise exercise) {
        return new ExerciseDto(
                exercise.getExerciseId(),
                exercise.getName(),
                exercise.getDescription()
        );
    }

    public static Exercise mapToExercise(ExerciseDto exerciseDto) {
        return new Exercise(
                exerciseDto.getExerciseId(),
                exerciseDto.getName(),
                exerciseDto.getDescription()
        );
    }
}
