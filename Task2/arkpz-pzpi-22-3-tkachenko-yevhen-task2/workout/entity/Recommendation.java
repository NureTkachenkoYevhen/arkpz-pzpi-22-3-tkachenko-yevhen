package com.tkachenko.yevhen.workout.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Recommendation() {
    }

    public Recommendation(Long recommendationId, User user, Exercise exercise, String message, LocalDateTime createdAt) {
        this.recommendationId = recommendationId;
        this.user = user;
        this.exercise = exercise;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public User getUser() {
        return user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setRecommendationId(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}