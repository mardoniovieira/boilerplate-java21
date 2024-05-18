package br.com.boilerplate.java21.presentation.exception.handler;

import br.com.boilerplate.java21.presentation.exception.ApiError;
import br.com.boilerplate.java21.presentation.exception.ApiErrorException;
import br.com.boilerplate.java21.presentation.exception.ApiErrorMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Handles Exception class.
     *
     * @param e the exception
     * @return the ApiException object
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleInternalServerError(RuntimeException e) {
        String errorMessage = messageSource.getMessage(ApiErrorMessage.UNKNOWN_ERROR.getProperty(), null, Locale.getDefault());
        ApiError apiError = new ApiError(errorMessage, e);
        LOGGER.error(getStackTrace(e));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles custom exception.
     *
     * @param e the exception
     * @return custom exception
     */
    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<Object> handleApiErrorException(ApiErrorException e, WebRequest request) {
        String errorMessage = messageSource.getMessage(e.getMessage(), null, Locale.getDefault());
        ApiError apiError = new ApiError(errorMessage, e);
        return new ResponseEntity<>(apiError, e.getStatus());
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
