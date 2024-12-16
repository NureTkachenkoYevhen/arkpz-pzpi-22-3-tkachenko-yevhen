package com.tkachenko.yevhen.workout.controller;

import com.tkachenko.yevhen.workout.dto.RecommendationDto;
import com.tkachenko.yevhen.workout.service.RecommendationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping
    public ResponseEntity<RecommendationDto> createRecommendation(@RequestBody RecommendationDto recommendationDto) {
        RecommendationDto savedRecommendation = recommendationService.createRecommendation(recommendationDto);
        return new ResponseEntity<>(savedRecommendation, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationDto>> getRecommendationsByUserId(@PathVariable("userId") Long userId) {
        List<RecommendationDto> recommendations = recommendationService.getRecommendationsByUserId(userId);
        return new ResponseEntity<>(recommendations, HttpStatus.OK);
    }

    @DeleteMapping("{recommendationId}")
    public ResponseEntity<String> deleteRecommendation(@PathVariable("recommendationId") Long recommendationId) {
        recommendationService.deleteRecommendation(recommendationId);
        return new ResponseEntity<>("Recommendation deleted successfully.", HttpStatus.OK);
    }

    @PutMapping("{recommendationId}")
    public ResponseEntity<RecommendationDto> updateRecommendation(
            @PathVariable("recommendationId") Long recommendationId,
            @RequestBody RecommendationDto updatedDto) {
        RecommendationDto updatedRecommendation = recommendationService.updateRecommendation(recommendationId, updatedDto);
        return new ResponseEntity<>(updatedRecommendation, HttpStatus.OK);
    }
}