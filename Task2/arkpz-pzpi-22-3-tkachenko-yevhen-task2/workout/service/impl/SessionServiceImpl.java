package com.tkachenko.yevhen.workout.service.impl;

import com.tkachenko.yevhen.workout.dto.SessionDto;
import com.tkachenko.yevhen.workout.entity.Exercise;
import com.tkachenko.yevhen.workout.entity.Session;
import com.tkachenko.yevhen.workout.entity.User;
import com.tkachenko.yevhen.workout.exception.ResourceNotFoundException;
import com.tkachenko.yevhen.workout.mapper.SessionMapper;
import com.tkachenko.yevhen.workout.repository.ExerciseRepository;
import com.tkachenko.yevhen.workout.repository.SessionRepository;
import com.tkachenko.yevhen.workout.repository.UserRepository;
import com.tkachenko.yevhen.workout.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public SessionServiceImpl(SessionRepository sessionRepository, UserRepository userRepository, ExerciseRepository exerciseRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public SessionDto createSession(SessionDto sessionDto) {
        User user = userRepository.findById(sessionDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + sessionDto.getUserId()));
        Exercise exercise = exerciseRepository.findById(sessionDto.getExerciseId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + sessionDto.getExerciseId()));

        Session session = SessionMapper.mapToSession(sessionDto, user, exercise);
        Session savedSession = sessionRepository.save(session);
        return SessionMapper.mapToSessionDto(savedSession);
    }

    @Override
    public SessionDto getSessionById(Long sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));
        return SessionMapper.mapToSessionDto(session);
    }

    @Override
    public List<SessionDto> getSessionsByUserId(Long userId) {
        List<Session> sessions = sessionRepository.findByUserUserId(userId);
        return sessions.stream().map(SessionMapper::mapToSessionDto).collect(Collectors.toList());
    }

    @Override
    public SessionDto updateSession(Long sessionId, SessionDto updatedSessionDto) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));

        User user = userRepository.findById(updatedSessionDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + updatedSessionDto.getUserId()));

        Exercise exercise = exerciseRepository.findById(updatedSessionDto.getExerciseId())
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found with id: " + updatedSessionDto.getExerciseId()));

        session.setUser(user);
        session.setExercise(exercise);
        session.setStartTime(updatedSessionDto.getStartTime());
        session.setEndTime(updatedSessionDto.getEndTime());
        session.setTotalReps(updatedSessionDto.getTotalReps());

        Session updatedSession = sessionRepository.save(session);

        return SessionMapper.mapToSessionDto(updatedSession);
    }

    @Override
    public void deleteSession(Long sessionId) {
        sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found with id: " + sessionId));

        sessionRepository.deleteById(sessionId);
    }
}

