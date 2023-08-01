package com.radoslawsawicki.backendreactnotesapp.exception;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.exception.WeatherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<Object> handleNoteNotFoundException(NoteNotFoundException exception) {
        return new ResponseEntity<>("Note with given id doesn't exist!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WeatherNotFoundException.class)
    public ResponseEntity<Object> handleWeatherNotFoundException(WeatherNotFoundException exception) {
        return new ResponseEntity<>("Weather with given id doesn't exist!", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CurrencyNotFoundException.class)
    public ResponseEntity<Object> handleCurrencyNotFoundException(CurrencyNotFoundException exception) {
        return new ResponseEntity<>("Currency with given id doesn't exist!", HttpStatus.BAD_REQUEST);
    }
}
