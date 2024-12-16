package com.tkachenko.yevhen.workout.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Exercise() {
    }

    public Exercise(Long exerciseId, String name, String description) {
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
