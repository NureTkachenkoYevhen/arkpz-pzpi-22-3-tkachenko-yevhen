package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.MetricDto;
import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.entity.Metric;
import com.tkachenko.yevhen.workout.entity.Session;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.MetricMapper;
import com.tkachenko.yevhen.workout.repository.MetricRepository;
import com.tkachenko.yevhen.workout.repository.SessionRepository;
import com.tkachenko.yevhen.workout.service.MetricService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetricServiceImpl implements MetricService {
    private final MetricRepository metricRepository;
    private final SessionRepository sessionRepository;

    public MetricServiceImpl(MetricRepository metricRepository,SessionRepository sessionRepository) {
        this.metricRepository = metricRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public MetricDto createMetric(MetricDto metricDto) {
        Session session = sessionRepository.findById(metricDto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + metricDto.getSessionId()));

        User user = session.getUser();
        float userHeight = user.getHeight();

        int newRepNumber = session.getTotalReps() + 1;
        session.setTotalReps(newRepNumber);

        LocalDateTime previousTimestamp = metricRepository.findTopBySessionSessionIdOrderByTimestampDesc(session.getSessionId())
                .map(Metric::getTimestamp)
                .orElse(null);

        float repTime = 2.5f;
        if (previousTimestamp != null) {
            repTime = (float) java.time.Duration.between(previousTimestamp, LocalDateTime.now()).toMillis() / 1000;
        }

        float correctnessScore = CorrectnessScoreCalculator.calculateCorrectnessScore(
                metricDto.getHeight(),
                metricDto.getTiltAngle(),
                newRepNumber,
                repTime,
                userHeight
        );

        Metric metric = new Metric();
        metric.setSession(session);
        metric.setRepNumber(newRepNumber);
        metric.setHeight(metricDto.getHeight());
        metric.setTiltAngle(metricDto.getTiltAngle());
        metric.setCorrectnessScore(correctnessScore);
        metric.setTimestamp(LocalDateTime.now());

        Metric savedMetric = metricRepository.save(metric);

        if (session.getStartTime() == null || metric.getTimestamp().isBefore(session.getStartTime())) {
            session.setStartTime(metric.getTimestamp());
        }
        if (session.getEndTime() == null || metric.getTimestamp().isAfter(session.getEndTime())) {
            session.setEndTime(metric.getTimestamp());
        }
        sessionRepository.save(session);

        return MetricMapper.mapToMetricDto(savedMetric);
    }

    @Override
    public List<MetricDto> getMetricsBySessionId(Long sessionId) {
        List<Metric> metrics = metricRepository.findBySessionSessionId(sessionId);
        return metrics.stream().map(MetricMapper::mapToMetricDto).collect(Collectors.toList());
    }

    @Override
    public void deleteMetric(Long metricId) {
        metricRepository.findById(metricId)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found with id: " + metricId));

        metricRepository.deleteById(metricId);
    }

    @Override
    public MetricDto updateMetric(Long metricId, MetricDto metricDto) {
        Metric metric = metricRepository.findById(metricId)
                .orElseThrow(() -> new ResourceNotFoundException("Metric not found with id: " + metricId));

        Session session = sessionRepository.findById(metricDto.getSessionId())
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + metricDto.getSessionId()));

        metric.setHeight(metricDto.getHeight());
        metric.setCorrectnessScore(metricDto.getCorrectnessScore());
        metric.setSession(session);
        metric.setTimestamp(metricDto.getTimestamp());
        metric.setRepNumber(metricDto.getRepNumber());

        Metric updatedMetric = metricRepository.save(metric);
        return MetricMapper.mapToMetricDto(updatedMetric);
    }
}