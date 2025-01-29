package com.radoslawsawicki.backendreactnotesapp.sharesapi.exception;

public class SharesProcessingException extends Exception {

    public static String DATA_ERROR = "Indexes incorrectly downloaded!";

    public SharesProcessingException(String message) {
        super(message);
    }
}
