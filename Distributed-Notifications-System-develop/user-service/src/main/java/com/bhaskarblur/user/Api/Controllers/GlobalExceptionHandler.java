package com.bhaskarblur.user.Api.Controllers;

import com.bhaskarblur.user.Api.Dtos.ApiStandardResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle ResponseStatusException separately for custom HTTP status and reason message
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiStandardResponse> handleResponseStatusException(ResponseStatusException ex) {
        ApiStandardResponse response = new ApiStandardResponse(
                false,
                ex.getReason(), // Custom message provided by ResponseStatusException
                null
        );

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    // Handle ConstraintViolationException to capture validation errors on @PathVariable or @RequestParam
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiStandardResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        // Join all violation messages into a single string
        String message = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));

        ApiStandardResponse response = new ApiStandardResponse(
                false,
                message,
                null
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}