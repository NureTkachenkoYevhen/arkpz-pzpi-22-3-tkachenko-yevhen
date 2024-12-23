package com.tkachenko.yevhen.workout.mapper;

import com.tkachenko.yevhen.workout.dto.RecommendationDto;
import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.entity.Recommendation;
import com.tkachenko.yevhen.workout.entity.Session;
import com.tkachenko.yevhen.workout.entity.User;

public class RecommendationMapper {
    public static RecommendationDto mapToRecommendationDto(Recommendation recommendation) {
        return new RecommendationDto(
                recommendation.getRecommendationId(),
                recommendation.getSession().getSessionId(),
                recommendation.getMessage(),
                recommendation.getCreatedAt()
        );
    }

    public static Recommendation mapToRecommendation(RecommendationDto recommendationDto, Session session) {
        return new Recommendation(
                recommendationDto.getRecommendationId(),
                session,
                recommendationDto.getMessage(),
                recommendationDto.getCreatedAt()
        );
    }
}
