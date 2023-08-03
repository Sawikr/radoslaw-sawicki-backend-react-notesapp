package com.radoslawsawicki.backendreactnotesapp.weatherapi.exception;

public class WeatherProcessingException extends Exception {

    public static String DATA_ERROR = "Forecast incorrectly downloaded!";

    public WeatherProcessingException(String message) {
        super(message);
    }
}
