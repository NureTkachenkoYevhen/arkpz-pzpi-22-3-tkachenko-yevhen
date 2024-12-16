package com.tkachenko.yevhen.workout.controller;

import com.tkachenko.yevhen.workout.dto.MetricDto;
import com.tkachenko.yevhen.workout.service.MetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricService metricService;

    public MetricController(MetricService metricService) {
        this.metricService = metricService;
    }

    @PostMapping
    public ResponseEntity<MetricDto> createMetric(@RequestBody MetricDto metricDto) {
        MetricDto savedMetric = metricService.createMetric(metricDto);
        return new ResponseEntity<>(savedMetric, HttpStatus.CREATED);
    }

    @GetMapping("{sessionId}")
    public ResponseEntity<List<MetricDto>> getMetricsBySessionId(@PathVariable("sessionId") Long sessionId) {
        List<MetricDto> metrics = metricService.getMetricsBySessionId(sessionId);
        return new ResponseEntity<>(metrics, HttpStatus.OK);
    }

    @DeleteMapping("{metricId}")
    public ResponseEntity<String> deleteMetric(@PathVariable("metricId") Long metricId) {
        metricService.deleteMetric(metricId);
        return new ResponseEntity<>("Metric deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("{metricId}")
    public ResponseEntity<MetricDto> updateMetric(
            @PathVariable("metricId") Long metricId,
            @RequestBody MetricDto updatedDto) {
        MetricDto updatedMetric = metricService.updateMetric(metricId, updatedDto);
        return new ResponseEntity<>(updatedMetric, HttpStatus.OK);
    }
}
