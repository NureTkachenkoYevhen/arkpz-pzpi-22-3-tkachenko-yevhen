package com.tkachenko.yevhen.workout.dto;

import java.time.LocalDateTime;

public class RecommendationDto {
    private Long recommendationId;
    private Long userId;
    private Long exerciseId;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();

    public RecommendationDto() {
    }

    public RecommendationDto(Long recommendationId, Long userId, Long exerciseId, String message, LocalDateTime createdAt) {
        this.recommendationId = recommendationId;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getExerciseId() {
        return exerciseId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
