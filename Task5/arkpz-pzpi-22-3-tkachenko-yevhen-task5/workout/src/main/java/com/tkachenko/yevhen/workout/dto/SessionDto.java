package com.tkachenko.yevhen.workout.dto;

import java.time.LocalDateTime;

public class SessionDto {
    private Long sessionId;
    private Long userId;
    private Long exerciseId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer totalReps;

    public SessionDto() {}

    public SessionDto(Long sessionId, Long userId, Long exerciseId, LocalDateTime startTime, LocalDateTime endTime, Integer totalReps) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.exerciseId = exerciseId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalReps = totalReps;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalReps() {
        return totalReps;
    }

    public void setTotalReps(Integer totalReps) {
        this.totalReps = totalReps;
    }
}
