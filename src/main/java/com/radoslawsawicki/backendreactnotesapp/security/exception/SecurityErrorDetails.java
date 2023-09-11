package com.radoslawsawicki.backendreactnotesapp.security.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SecurityErrorDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String details;
}
