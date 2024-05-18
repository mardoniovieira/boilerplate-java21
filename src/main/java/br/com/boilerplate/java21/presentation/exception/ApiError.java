package br.com.boilerplate.java21.presentation.exception;

import lombok.Data;

@Data
public class ApiError {

    private String message;

    public ApiError(String message, Throwable ex) {
        this.message = message;
    }
}
