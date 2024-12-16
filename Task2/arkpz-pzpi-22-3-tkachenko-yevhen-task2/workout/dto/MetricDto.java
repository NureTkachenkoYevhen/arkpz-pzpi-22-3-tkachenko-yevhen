package com.tkachenko.yevhen.workout.dto;


import java.time.LocalDateTime;

public class MetricDto {
    private Long metricId;

    private Long sessionId;

    private LocalDateTime timestamp;

    private Integer repNumber;

    private Float height;

    private Float correctnessScore;

    public MetricDto() {
    }

    public MetricDto(Long metricId, Long sessionId, LocalDateTime timestamp, Integer repNumber, Float height, Float correctnessScore) {
        this.metricId = metricId;
        this.sessionId = sessionId;
        this.timestamp = timestamp;
        this.repNumber = repNumber;
        this.height = height;
        this.correctnessScore = correctnessScore;
    }

    public Long getMetricId() {
        return metricId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getRepNumber() {
        return repNumber;
    }

    public Float getHeight() {
        return height;
    }

    public Float getCorrectnessScore() {
        return correctnessScore;
    }

    public void setMetricId(Long metricId) {
        this.metricId = metricId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setRepNumber(Integer repNumber) {
        this.repNumber = repNumber;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setCorrectnessScore(Float correctnessScore) {
        this.correctnessScore = correctnessScore;
    }
}
