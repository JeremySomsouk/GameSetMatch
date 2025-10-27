package com.jsomsouk.tennis.kata.GameSetMatch.infrastructure.web;

import com.jsomsouk.tennis.kata.GameSetMatch.application.exception.GameNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(Map.of(
            "error", "BAD_REQUEST",
            "message", exception.getMessage()
        ));
    }

    @ExceptionHandler(GameNotFound.class)
    public ResponseEntity<Map<String, Object>> handleGameNotFound(GameNotFound exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "GameNotFound");
        body.put("message", exception.getMessage());
        body.put("path", ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
