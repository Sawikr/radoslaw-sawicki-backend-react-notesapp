package com.radoslawsawicki.backendreactnotesapp.weather;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.repository.WeatherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class WeatherTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherTestSuite.class);

    @Autowired
    private WeatherRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateWeatherTest() {
        //Given
        log.info("Starting test: shouldFetchCreateWeatherTest");

        Weather weather = new Weather( 25.6F, 1005, 85, 1.5F);

        //When
        repository.save(weather);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void shouldFetchGetAllWeathersTest() {
        //Given
        log.info("Starting test: shouldFetchGetAllWeathersTest");

        Weather weather1 = new Weather( 25.6F, 1005, 85, 1.5F);
        Weather weather2 = new Weather( 25.6F, 1005, 85, 1.5F);

        //When
        repository.save(weather1);
        repository.save(weather2);

        //Then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldFetchGetWeatherByIdTest() {
        //Given
        log.info("Starting test: shouldFetchGetWeatherByIdTest");

        Weather weather = new Weather( 25.6F, 1005, 85, 1.5F);

        //When
        repository.save(weather);

        //Then
        assertTrue(repository.existsById(weather.getId()));
    }

    @Test
    void shouldFetchDeleteWeather() {
        //Given
        log.info("Starting test: shouldFetchDeleteWeather");

        Weather weather1 = new Weather( 25.6F, 1005, 85, 1.5F);
        Weather weather2 = new Weather( 25.6F, 1005, 85, 1.5F);

        //When
        repository.save(weather1);
        repository.save(weather2);
        repository.delete(weather1);

        //Then
        assertEquals(1, repository.count());
    }
}
