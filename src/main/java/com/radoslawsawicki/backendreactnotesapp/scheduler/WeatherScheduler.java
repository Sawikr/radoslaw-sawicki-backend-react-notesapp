package com.radoslawsawicki.backendreactnotesapp.scheduler;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.facade.CurrencyFacade;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.exception.WeatherProcessingException;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.facade.WeatherFacade;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.mapper.WeatherMapper;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherScheduler {

    private static final String DESCRIPTION = "Forecast download was successful!";
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyFacade.class);
    private final WeatherFacade facade;
    private final WeatherService service;
    private final WeatherMapper mapper;
    private final Weather weather;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void getAndSaveWeatherScheduler() throws WeatherProcessingException {
        WeatherDto weatherScheduler = facade.processGetWeatherScheduler();
        service.saveWeather(mapper.mapToWeather(weatherScheduler));
        LOGGER.info(DESCRIPTION);
    }
}