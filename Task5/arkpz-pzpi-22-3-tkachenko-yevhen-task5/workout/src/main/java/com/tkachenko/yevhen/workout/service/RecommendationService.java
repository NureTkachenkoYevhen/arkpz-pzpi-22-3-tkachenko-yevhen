package com.tkachenko.yevhen.workout.service;

import com.tkachenko.yevhen.workout.dto.RecommendationDto;

import java.util.List;

public interface RecommendationService {

    RecommendationDto createRecommendation(RecommendationDto recommendationDto);

    RecommendationDto generateSessionRecommendation(Long sessionId);

    List<RecommendationDto> getRecommendationsByUserId(Long userId);

    void deleteRecommendation(Long recommendationId);

    RecommendationDto updateRecommendation(Long recommendationId, RecommendationDto recommendationDto);
}
