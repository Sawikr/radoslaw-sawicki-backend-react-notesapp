package com.radoslawsawicki.backendreactnotesapp.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalSecurityErrorHandler {

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Object> handleUsernameSecurityException(SecurityException exception, WebRequest webRequest) {

        SecurityErrorDetails securityErrorDetails = getSecurityErrorDetails(exception, webRequest);

        return new ResponseEntity<>(securityErrorDetails, HttpStatus.BAD_REQUEST);
    }

    private SecurityErrorDetails getSecurityErrorDetails(SecurityException exception, WebRequest webRequest) {
        return new SecurityErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist!", HttpStatus.BAD_REQUEST);
    }
}