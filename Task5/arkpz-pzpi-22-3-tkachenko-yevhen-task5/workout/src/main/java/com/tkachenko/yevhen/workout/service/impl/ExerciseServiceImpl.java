package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.ExerciseDto;
import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.ExerciseMapper;
import com.tkachenko.yevhen.workout.repository.ExerciseRepository;
import com.tkachenko.yevhen.workout.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto exerciseDto) {
        Exercise exercise = ExerciseMapper.mapToExercise(exerciseDto);
        Exercise savedExercise = exerciseRepository.save(exercise);
        return ExerciseMapper.mapToExerciseDto(savedExercise);
    }

    @Override
    public ExerciseDto getExerciseById(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exerciseId));
        return ExerciseMapper.mapToExerciseDto(exercise);
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(ExerciseMapper::mapToExerciseDto).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto updateExercise(Long exerciseId, ExerciseDto updatedExerciseDto) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exerciseId));

        exercise.setName(updatedExerciseDto.getName());
        exercise.setDescription(updatedExerciseDto.getDescription());

        Exercise updatedExercise = exerciseRepository.save(exercise);
        return ExerciseMapper.mapToExerciseDto(updatedExercise);
    }

    @Override
    public void deleteExercise(Long exerciseId) {
        exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + exerciseId));

        exerciseRepository.deleteById(exerciseId);
    }
}

