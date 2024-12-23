package com.tkachenko.yevhen.workout.repository;

import com.tkachenko.yevhen.workout.entity.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetricRepository extends JpaRepository<Metric, Long> {
    List<Metric> findBySessionSessionId(Long sessionId);
    Optional<Metric> findTopBySessionSessionIdOrderByTimestampDesc(Long sessionId);
}
