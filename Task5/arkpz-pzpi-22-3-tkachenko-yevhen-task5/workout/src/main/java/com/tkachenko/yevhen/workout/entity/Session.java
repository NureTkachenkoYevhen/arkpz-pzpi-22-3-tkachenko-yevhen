package com.tkachenko.yevhen.workout.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "total_reps", nullable = false)
    private Integer totalReps;

    public Session() {
    }

    public Session(Long sessionId, User user, Exercise exercise, LocalDateTime startTime, LocalDateTime endTime, Integer totalReps) {
        this.sessionId = sessionId;
        this.user = user;
        this.exercise = exercise;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalReps = totalReps;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public User getUser() {
        return user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Integer getTotalReps() {
        return totalReps;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setTotalReps(Integer totalReps) {
        this.totalReps = totalReps;
    }
}