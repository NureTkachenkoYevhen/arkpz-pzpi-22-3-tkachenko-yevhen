package com.tkachenko.yevhen.workout.controller;

import com.tkachenko.yevhen.workout.dto.SessionDto;
import com.tkachenko.yevhen.workout.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionDto sessionDto) {
        SessionDto savedSession = sessionService.createSession(sessionDto);
        return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
    }

    @GetMapping("{sessionId}")
    public ResponseEntity<SessionDto> getSessionById(@PathVariable("sessionId") Long sessionId) {
        SessionDto sessionDto = sessionService.getSessionById(sessionId);
        return new ResponseEntity<>(sessionDto, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SessionDto>> getSessionsByUserId(@PathVariable("userId") Long userId) {
        List<SessionDto> sessions = sessionService.getSessionsByUserId(userId);
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @PutMapping("{sessionId}")
    public ResponseEntity<SessionDto> updateSession(
            @PathVariable("sessionId") Long sessionId,
            @RequestBody SessionDto updatedDto) {
        SessionDto updatedSession = sessionService.updateSession(sessionId, updatedDto);
        return new ResponseEntity<>(updatedSession, HttpStatus.OK);
    }

    @DeleteMapping("{sessionId}")
    public ResponseEntity<String> deleteSession(@PathVariable("sessionId") Long sessionId) {
        sessionService.deleteSession(sessionId);
        return new ResponseEntity<>("Session deleted successfully.", HttpStatus.OK);
    }
}
