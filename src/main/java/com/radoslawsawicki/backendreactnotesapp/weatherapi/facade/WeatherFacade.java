package com.radoslawsawicki.backendreactnotesapp.weatherapi.facade;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyProcessingException;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.exception.WeatherProcessingException;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class WeatherFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherFacade.class);
    private final WeatherService service;

    @Autowired
    public WeatherFacade(WeatherService service) {
        this.service = service;
    }

    public Optional<WeatherDto> processGetWeather() throws WeatherProcessingException {

        Optional<WeatherDto> result = Optional.ofNullable(service.getWeather());
        boolean wasError = false;
        if (result.isEmpty()) {
            LOGGER.error(WeatherProcessingException.DATA_ERROR);
            wasError = true;
            throw new WeatherProcessingException(CurrencyProcessingException.DATA_ERROR);
        }
        return result;
    }
}
