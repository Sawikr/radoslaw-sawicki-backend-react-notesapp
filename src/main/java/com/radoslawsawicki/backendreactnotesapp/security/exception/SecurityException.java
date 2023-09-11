package com.radoslawsawicki.backendreactnotesapp.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class SecurityException extends RuntimeException {

    private HttpStatus status;
    private String message;
}
