package com.darmokhval.crm_programming_school.exception;

public class ProblemWithJwtException extends RuntimeException{
    public ProblemWithJwtException(String message) {
        super(message);
    }

    public ProblemWithJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
