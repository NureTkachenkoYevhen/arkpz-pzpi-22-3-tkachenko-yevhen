package com.tkachenko.yevhen.workout.mapper;

import com.tkachenko.yevhen.workout.dto.RecommendationDto;
import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.entity.Recommendation;
import com.tkachenko.yevhen.workout.entity.User;

public class RecommendationMapper {
    public static RecommendationDto mapToRecommendationDto(Recommendation recommendation) {
        return new RecommendationDto(
                recommendation.getRecommendationId(),
                recommendation.getUser().getUserId(),
                recommendation.getExercise().getExerciseId(),
                recommendation.getMessage(),
                recommendation.getCreatedAt()
        );
    }

    public static Recommendation mapToRecommendation(RecommendationDto recommendationDto, User user, Exercise exercise) {
        return new Recommendation(
                recommendationDto.getRecommendationId(),
                user,
                exercise,
                recommendationDto.getMessage(),
                recommendationDto.getCreatedAt()
        );
    }
}
