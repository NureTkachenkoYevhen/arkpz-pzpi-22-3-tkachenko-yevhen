package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.RecommendationDto;
import com.tkachenko.yevhen.workout.entity.Metric;
import com.tkachenko.yevhen.workout.entity.Recommendation;
import com.tkachenko.yevhen.workout.entity.Session;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.RecommendationMapper;
import com.tkachenko.yevhen.workout.repository.MetricRepository;
import com.tkachenko.yevhen.workout.repository.RecommendationRepository;
import com.tkachenko.yevhen.workout.repository.SessionRepository;
import com.tkachenko.yevhen.workout.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final SessionRepository sessionRepository;
    private final MetricRepository metricRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository, SessionRepository sessionRepository, MetricRepository metricRepository) {
        this.recommendationRepository = recommendationRepository;
        this.sessionRepository = sessionRepository;
        this.metricRepository = metricRepository;
    }

    @Override
    public RecommendationDto createRecommendation(RecommendationDto recommendationDto) {
        Session session = sessionRepository.findById(recommendationDto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + recommendationDto.getSessionId()));

        Recommendation recommendation = RecommendationMapper.mapToRecommendation(recommendationDto, session);
        Recommendation savedRecommendation = recommendationRepository.save(recommendation);
        return RecommendationMapper.mapToRecommendationDto(savedRecommendation);
    }

    @Override
    public RecommendationDto generateSessionRecommendation(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));

        List<Metric> metrics = metricRepository.findBySessionSessionId(sessionId);
        float userHeight = session.getUser().getHeight();

        String recommendationMessage = generateSessionRecommendation(metrics, userHeight);

        Recommendation recommendation = new Recommendation();
        recommendation.setSession(session);
        recommendation.setMessage(recommendationMessage);

        Recommendation savedRecommendation = recommendationRepository.save(recommendation);

        return RecommendationMapper.mapToRecommendationDto(savedRecommendation);
    }

    private static String generateSessionRecommendation(List<Metric> sessionMetrics, float userHeight) {
        float optimalHeight = 0.4f * userHeight;
        float maxTiltAngle = 15.0f;
        float optimalRepTime = 2.5f;
        sessionMetrics.sort(Comparator.comparing(Metric::getTimestamp));

        List<Float> repTimes = new ArrayList<>();
        for (int i = 1; i < sessionMetrics.size(); i++) {
            Metric previousMetric = sessionMetrics.get(i - 1);
            Metric currentMetric = sessionMetrics.get(i);
            float repTime = (float) java.time.Duration.between(previousMetric.getTimestamp(), currentMetric.getTimestamp()).toMillis() / 1000;
            repTimes.add(repTime);
        }

        if (!sessionMetrics.isEmpty() && sessionMetrics.getFirst().getRepNumber() == 0) {
            repTimes.add(optimalRepTime);
        }

        float averageHeight = (float) sessionMetrics.stream().mapToDouble(Metric::getHeight).average().orElse(0.0);
        float averageTiltAngle = (float) sessionMetrics.stream().mapToDouble(Metric::getTiltAngle).average().orElse(0.0);
        float averageRepTime = (float) repTimes.stream().mapToDouble(Float::doubleValue).average().orElse(0.0);
        float averageCorrectnessScore = (float) sessionMetrics.stream().mapToDouble(Metric::getCorrectnessScore).average().orElse(0.0);

        StringBuilder recommendation = new StringBuilder();

        if (averageHeight < optimalHeight * 0.9) {
            recommendation.append("Середня висота підтягувань недостатня. Постарайтеся піднімати підборіддя вище турніка. ");
        } else if (averageHeight > optimalHeight * 1.1) {
            recommendation.append("Середня висота підтягувань занадто висока, що може спричинити перенапруження. Виконуйте рух плавно. ");
        }

        if (averageTiltAngle > maxTiltAngle * 0.8) {
            recommendation.append("Середній кут нахилу тулуба занадто великий. Слідкуйте за рівним положенням спини під час підтягувань. ");
        }

        if (averageRepTime < optimalRepTime * 0.5) {
            recommendation.append("Середній час між повтореннями занадто короткий. Збільшіть час між повтореннями для кращого контролю. ");
        } else if (averageRepTime > optimalRepTime * 1.5) {
            recommendation.append("Середній час між повтореннями занадто довгий. Намагайтеся виконувати підтягування ритмічно. ");
        }

        if (averageCorrectnessScore < 0.7) {
            recommendation.append("Загальна техніка виконання потребує покращення. Перегляньте основні моменти руху, щоб уникнути помилок. ");
        }

        if (recommendation.isEmpty()) {
            recommendation.append("Техніка виконання виглядає добре. Продовжуйте в тому ж дусі!");
        } else {
            recommendation.append("Працюйте над технікою, щоб досягти кращих результатів!");
        }

        return recommendation.toString();
    }

    @Override
    public List<RecommendationDto> getRecommendationsByUserId(Long userId) {
        List<Recommendation> recommendations = recommendationRepository.findBySessionUserUserId(userId);
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

        Session session = sessionRepository.findById(recommendationDto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + recommendationDto.getSessionId()));

        recommendation.setSession(session);
        recommendation.setMessage(recommendationDto.getMessage());

        Recommendation updatedRecommendation = recommendationRepository.save(recommendation);
        return RecommendationMapper.mapToRecommendationDto(updatedRecommendation);
    }
}
