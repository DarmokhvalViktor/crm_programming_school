package com.darmokhval.crm_programming_school.controller;

import com.darmokhval.crm_programming_school.exception.ProblemWithJwtException;
import com.darmokhval.crm_programming_school.exception.SQLScriptExecutorError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProblemWithJwtException.class)
    public ResponseEntity<Map<String, Object>> handleProblemWithJwtException(
            ProblemWithJwtException exception, WebRequest request) {
        return formErrorResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), request);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException exception, WebRequest request) {
        return formErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception, WebRequest request) {
        List<String> errorMessages = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return formErrorResponse(HttpStatus.BAD_REQUEST, errorMessages, request);
    }
    @ExceptionHandler(SQLScriptExecutorError.class)
    public ResponseEntity<Map<String, Object>> handleSQLScriptExecutorError(
            SQLScriptExecutorError exception, WebRequest request) {
        return formErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), request);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException exception, WebRequest request) {
        String errorMessage = String.format("Invalid request data: %s", exception.getMessage());
        return formErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, request);
    }
    private ResponseEntity<Map<String, Object>> formErrorResponse(HttpStatus status, String message, WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", System.currentTimeMillis());
        responseBody.put("status", status.value());
        responseBody.put("error", status.getReasonPhrase());
        responseBody.put("message", message);
        responseBody.put("path", request.getDescription(false));
        return new ResponseEntity<>(responseBody, status);
    }
    private ResponseEntity<Map<String, Object>> formErrorResponse(
            HttpStatus status, List<String> messages, WebRequest request) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", System.currentTimeMillis());
        responseBody.put("status", status.value());
        responseBody.put("error", status.getReasonPhrase());
        responseBody.put("messages", messages);
        responseBody.put("path", request.getDescription(false));

        return new ResponseEntity<>(responseBody, status);
    }
}
