package com.tkachenko.yevhen.workout.controller;

import com.tkachenko.yevhen.workout.dto.ExerciseDto;
import com.tkachenko.yevhen.workout.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @PostMapping
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto exerciseDto) {
        ExerciseDto savedExercise = exerciseService.createExercise(exerciseDto);
        return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
    }

    @GetMapping("{exerciseId}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable("exerciseId") Long exerciseId) {
        ExerciseDto exerciseDto = exerciseService.getExerciseById(exerciseId);
        return new ResponseEntity<>(exerciseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDto>> getAllExercises() {
        List<ExerciseDto> exercises = exerciseService.getAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @PutMapping("{exerciseId}")
    public ResponseEntity<ExerciseDto> updateExercise(
            @PathVariable("exerciseId") Long exerciseId,
            @RequestBody ExerciseDto updatedDto) {
        ExerciseDto updatedExercise = exerciseService.updateExercise(exerciseId, updatedDto);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable("exerciseId") Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return new ResponseEntity<>("Exercise deleted successfully.", HttpStatus.OK);
    }
}