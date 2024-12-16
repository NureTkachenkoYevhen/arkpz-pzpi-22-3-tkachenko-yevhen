package com.tkachenko.yevhen.workout.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "rep_number", nullable = false)
    private Integer repNumber;

    @Column(nullable = false)
    private Float height;

    @Column(name = "correctness_score", nullable = false)
    private Float correctnessScore;

    public Metric() {
    }

    public Metric(Long metricId, Session session, LocalDateTime timestamp, Integer repNumber, Float height, Float correctnessScore) {
        this.metricId = metricId;
        this.session = session;
        this.timestamp = timestamp;
        this.repNumber = repNumber;
        this.height = height;
        this.correctnessScore = correctnessScore;
    }

    public Long getMetricId() {
        return metricId;
    }

    public Session getSession() {
        return session;
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

    public void setSession(Session session) {
        this.session = session;
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
