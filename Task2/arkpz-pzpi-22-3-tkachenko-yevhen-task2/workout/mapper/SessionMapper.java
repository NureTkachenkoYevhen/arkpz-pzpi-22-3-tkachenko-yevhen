package com.tkachenko.yevhen.workout.mapper;

import com.tkachenko.yevhen.workout.dto.SessionDto;
import com.tkachenko.yevhen.workout.entity.Session;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.entity.Exercise;

public class SessionMapper {
    public static SessionDto mapToSessionDto(Session session) {
        return new SessionDto(
                session.getSessionId(),
                session.getUser().getUserId(),
                session.getExercise().getExerciseId(),
                session.getStartTime(),
                session.getEndTime(),
                session.getTotalReps()
        );
    }

    public static Session mapToSession(SessionDto sessionDto, User user, Exercise exercise) {
        Session session = new Session();
        session.setSessionId(sessionDto.getSessionId());
        session.setUser(user);
        session.setExercise(exercise);
        session.setStartTime(sessionDto.getStartTime());
        session.setEndTime(sessionDto.getEndTime());
        session.setTotalReps(sessionDto.getTotalReps());
        return session;
    }
}
