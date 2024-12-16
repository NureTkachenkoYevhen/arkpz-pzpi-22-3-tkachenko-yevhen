package com.tkachenko.yevhen.workout.dto;

public class ExerciseDto {
    private Long exerciseId;
    private String name;
    private String description;

    public ExerciseDto() {
    }

    public ExerciseDto(Long exerciseId, String name, String description) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
