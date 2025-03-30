package com.example.springboot03.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // Handle NotFoundException
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    // Handle BadRequestException
    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(BadRequestException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    // Handle validation exceptions from @Valid annotations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Failed");

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    // Handle validation for method parameters
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();

        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName();

            for (var messageError : parameterError.getResolvableErrors()) {
                errors.put(paramName, messageError.getDefaultMessage());
            }
        });

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Method Parameter Validation Failed");
        problemDetail.setProperties(Map.of(
                "timestamp", LocalDateTime.now(),
                "errors", errors // Attach validation errors
        ));

        return problemDetail;
    }

    // Handle generic internal server error
    @ExceptionHandler(Exception.class)
    public ProblemDetail handleInternalServerError(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    // Handle UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public ProblemDetail handleUnauthorizedException(UnauthorizedException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    // Handle ResourceConflictException
    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail handleResourceConflictException(ResourceConflictException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
}
