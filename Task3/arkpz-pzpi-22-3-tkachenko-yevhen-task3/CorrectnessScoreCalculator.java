package com.tkachenko.yevhen.workout.service.impl;

public class CorrectnessScoreCalculator {

    private static final float MAX_TILT_ANGLE = 15.0f;
    private static final float OPTIMAL_REP_TIME = 2.5f;
    private static final float HEIGHT_WEIGHT = 0.4f;
    private static final float TILT_ANGLE_WEIGHT = 0.3f;
    private static final float TIME_WEIGHT = 0.3f;

    public static float calculateCorrectnessScore(float actualHeight, float tiltAngle, int repNumber, float repTime, float userHeight) {

        float armLength = 0.4f * userHeight;

        float heightScore = calculateHeightScore(actualHeight, armLength);

        float tiltAngleScore = calculateTiltAngleScore(tiltAngle);

        float timeScore = calculateTimeScore(repTime);

        float correctnessScore = (HEIGHT_WEIGHT * heightScore)
                + (TILT_ANGLE_WEIGHT * tiltAngleScore)
                + (TIME_WEIGHT * timeScore);

        return Math.round(correctnessScore * 100) / 100.0f;
    }

    private static float calculateHeightScore(float actualHeight, float optimalHeight) {
        float difference = Math.abs(actualHeight - optimalHeight);
        return Math.max(0.0f, 1.0f - (difference / optimalHeight));
    }

    private static float calculateTiltAngleScore(float tiltAngle) {
        if (tiltAngle <= MAX_TILT_ANGLE) {
            return 1.0f;
        } else {
            float penalty = (tiltAngle - MAX_TILT_ANGLE) / MAX_TILT_ANGLE;
            return Math.max(0.0f, 1.0f - penalty);
        }
    }

    private static float calculateTimeScore(float repTime) {
        float deviation = Math.abs(repTime - OPTIMAL_REP_TIME);
        float maxDeviation = OPTIMAL_REP_TIME * 0.5f;
        if (deviation <= maxDeviation) {
            return 1.0f;
        } else {
            float penalty = deviation / maxDeviation;
            return Math.max(0.0f, 1.0f - penalty);
        }
    }
}
