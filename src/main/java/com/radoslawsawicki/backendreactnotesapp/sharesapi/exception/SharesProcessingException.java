package com.radoslawsawicki.backendreactnotesapp.sharesapi.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class SharesProcessingException extends HttpClientErrorException {

    public static String DATA_ERROR = "Indexes incorrectly downloaded!";

    public SharesProcessingException(String message) {
        super(HttpStatusCode.valueOf(401), message);
    }
}
