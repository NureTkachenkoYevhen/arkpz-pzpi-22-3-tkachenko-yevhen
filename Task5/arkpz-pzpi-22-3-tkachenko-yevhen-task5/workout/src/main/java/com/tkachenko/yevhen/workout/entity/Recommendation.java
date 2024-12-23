package com.tkachenko.yevhen.workout.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;

    @OneToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Recommendation() {
    }

    public Recommendation(Long recommendationId, Session session, String message, LocalDateTime createdAt) {
        this.recommendationId = recommendationId;
        this.session = session;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Long getRecommendationId() {
        return recommendationId;
    }

    public Session getSession() {
        return session;
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

    public void setSession(Session session) {
        this.session = session;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}