package com.tkachenko.yevhen.workout.service;

import com.tkachenko.yevhen.workout.dto.SessionDto;

import java.util.List;

public interface SessionService {
    SessionDto createSession(SessionDto sessionDto);

    SessionDto getSessionById(Long sessionId);

    List<SessionDto> getSessionsByUserId(Long userId);

    SessionDto updateSession(Long sessionId, SessionDto updatedSessionDto);

    void deleteSession(Long sessionId);
}
