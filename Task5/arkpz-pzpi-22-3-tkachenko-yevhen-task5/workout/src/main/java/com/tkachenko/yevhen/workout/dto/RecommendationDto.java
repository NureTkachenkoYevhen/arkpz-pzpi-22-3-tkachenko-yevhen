package com.tkachenko.yevhen.workout.dto;

import java.time.LocalDateTime;

public class RecommendationDto {
    private Long recommendationId;
    private Long sessionId;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();

    public RecommendationDto() {
    }

    public RecommendationDto(Long recommendationId, Long sessionId, String message, LocalDateTime createdAt) {
        this.recommendationId = recommendationId;
        this.sessionId = sessionId;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public Long getSessionId() {
        return sessionId;
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

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
