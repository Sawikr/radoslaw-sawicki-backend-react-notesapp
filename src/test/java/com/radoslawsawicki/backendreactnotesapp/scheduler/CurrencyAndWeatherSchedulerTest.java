package com.radoslawsawicki.backendreactnotesapp.scheduler;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.client.CurrencyClient;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.client.WeatherClient;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class CurrencyAndWeatherSchedulerTest {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CurrencyAndWeatherSchedulerTest.class);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.out.println("--------------------------");
    }

    @InjectMocks
    private CurrencyClient currencyClient;

    @InjectMocks
    private WeatherClient weatherClient;

    @Test
    public void shouldGetCurrency() {
        //Given
        log.info("Starting test: shouldGetCurrency");

        String code = "eur";

        //When
        Optional<CurrencyDto> currency = Optional.ofNullable(currencyClient.getExchangeRates(code));

        //Then
        assertFalse(currency.isEmpty());
    }

    @Test
    public void shouldGetForecast() {
        //Given
        log.info("Starting test: shouldGetForecast");

        String city = "poznan";

        //Given & When
        Optional<WeatherDto> weather = Optional.ofNullable(weatherClient.getWeatherForCity(city));

        //Then
        assertFalse(weather.isEmpty());
    }
}
