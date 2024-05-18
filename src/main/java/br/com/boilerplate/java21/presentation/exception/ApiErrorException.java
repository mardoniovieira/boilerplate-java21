package br.com.boilerplate.java21.presentation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    /**
     * Constructor with a response status, message and key to add to the exception.
     *
     * @param status  the HTTP status (required)
     * @param message the error message (required)
     */
    public ApiErrorException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
