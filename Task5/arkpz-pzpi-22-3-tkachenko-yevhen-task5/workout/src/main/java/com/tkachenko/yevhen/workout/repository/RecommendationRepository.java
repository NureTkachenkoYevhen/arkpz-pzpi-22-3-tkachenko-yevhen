package com.tkachenko.yevhen.workout.repository;

import com.tkachenko.yevhen.workout.entity.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findBySessionUserUserId(Long userId);
}
