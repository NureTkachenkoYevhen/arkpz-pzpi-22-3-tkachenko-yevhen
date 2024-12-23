package com.tkachenko.yevhen.workout.service;

import com.tkachenko.yevhen.workout.dto.MetricDto;
import com.tkachenko.yevhen.workout.dto.RecommendationDto;

import java.util.List;

public interface MetricService {

    MetricDto createMetric(MetricDto metricDto);

    List<MetricDto> getMetricsBySessionId(Long sessionId);

    void deleteMetric(Long metricId);

    MetricDto updateMetric(Long metricId, MetricDto metricDto);
}
