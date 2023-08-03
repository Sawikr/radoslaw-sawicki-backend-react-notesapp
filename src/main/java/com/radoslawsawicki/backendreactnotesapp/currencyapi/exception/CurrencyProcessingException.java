package com.radoslawsawicki.backendreactnotesapp.currencyapi.exception;

public class CurrencyProcessingException extends Exception {

    public static String DATA_ERROR = "Currency exchange rate incorrectly downloaded!";

    public CurrencyProcessingException(String message) {
        super(message);
    }
}
