package com.tkachenko.yevhen.workout.mapper;

import com.tkachenko.yevhen.workout.dto.MetricDto;
import com.tkachenko.yevhen.workout.entity.Metric;
import com.tkachenko.yevhen.workout.entity.Session;

public class MetricMapper {
    public static MetricDto mapToMetricDto(Metric metric) {
        return new MetricDto(
                metric.getMetricId(),
                metric.getSession().getSessionId(),
                metric.getTimestamp(),
                metric.getRepNumber(),
                metric.getHeight(),
                metric.getCorrectnessScore()
        );
    }

    public static Metric mapToMetric(MetricDto metricDto, Session session) {
        return new Metric(
                metricDto.getMetricId(),
                session,
                metricDto.getTimestamp(),
                metricDto.getRepNumber(),
                metricDto.getHeight(),
                metricDto.getCorrectnessScore()
        );
    }
}
