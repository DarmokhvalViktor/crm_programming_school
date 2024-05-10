package com.darmokhval.crm_programming_school.exception;

public class SQLScriptExecutorError extends RuntimeException {
    public SQLScriptExecutorError(String message, Throwable cause) {
        super(message, cause);
    }
}
