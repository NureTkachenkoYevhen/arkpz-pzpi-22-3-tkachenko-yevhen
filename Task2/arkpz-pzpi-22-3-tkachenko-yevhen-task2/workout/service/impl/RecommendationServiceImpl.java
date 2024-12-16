package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.RecommendationDto;
import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.entity.Recommendation;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.RecommendationMapper;
import com.tkachenko.yevhen.workout.repository.ExerciseRepository;
import com.tkachenko.yevhen.workout.repository.RecommendationRepository;
import com.tkachenko.yevhen.workout.repository.UserRepository;
import com.tkachenko.yevhen.workout.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public RecommendationDto createRecommendation(RecommendationDto recommendationDto) {
        User user = userRepository.findById(recommendationDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + recommendationDto.getUserId()));
        Exercise exercise = exerciseRepository.findById(recommendationDto.getExerciseId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + recommendationDto.getExerciseId()));

        Recommendation recommendation = RecommendationMapper.mapToRecommendation(recommendationDto, user, exercise);
        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return RecommendationMapper.mapToRecommendationDto(savedRecommendation);
    }

    @Override
    public List<RecommendationDto> getRecommendationsByUserId(Long userId) {
        List<Recommendation> recommendations = recommendationRepository.findByUserUserId(userId);
        return recommendations.stream().map(RecommendationMapper::mapToRecommendationDto).collect(Collectors.toList());
    }

    @Override
    public void deleteRecommendation(Long recommendationId) {
        recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + recommendationId));
        recommendationRepository.deleteById(recommendationId);
    }

    @Override
    public RecommendationDto updateRecommendation(Long recommendationId, RecommendationDto recommendationDto) {
        Recommendation recommendation = recommendationRepository.findById(recommendationId)
                .orElseThrow(() -> new ResourceNotFoundException("Recommendation not found with id: " + recommendationId));

        User user = userRepository.findById(recommendationDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + recommendationDto.getUserId()));
        Exercise exercise = exerciseRepository.findById(recommendationDto.getExerciseId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + recommendationDto.getExerciseId()));

        recommendation.setUser(user);
        recommendation.setExercise(exercise);
        recommendation.setMessage(recommendationDto.getMessage());

        Recommendation updatedRecommendation = recommendationRepository.save(recommendation);
        return RecommendationMapper.mapToRecommendationDto(updatedRecommendation);
    }
}
