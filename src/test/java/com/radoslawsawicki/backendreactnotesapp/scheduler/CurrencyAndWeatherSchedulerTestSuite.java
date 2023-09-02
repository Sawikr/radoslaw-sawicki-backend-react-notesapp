package com.radoslawsawicki.backendreactnotesapp.scheduler;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.client.CurrencyClient;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Rate;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.client.WeatherClient;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyAndWeatherSchedulerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CurrencyAndWeatherSchedulerTestSuite.class);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.out.println("--------------------------");
    }

    @Mock
    private CurrencyClient currencyClient;

    @Mock
    private WeatherClient weatherClient;

    @Test
    public void shouldFetchGetCurrency() {
        //Given
        log.info("Starting test: shouldFetchGetCurrency");

        String code = "eur";
        List<Rate> rates = List.of(new Rate("161/A/NBP/2023", "2023-08-22", 4.4524));
        CurrencyDto currencyDto = new CurrencyDto("A", "Euro", "eur", rates);

        when(currencyClient.getExchangeRates(code)).thenReturn(currencyDto);

        //When
        Optional<CurrencyDto> currency = Optional.ofNullable(currencyClient.getExchangeRates(code));

        //Then
        assertFalse(currency.isEmpty());
    }

    @Test
    public void shouldFetchGetForecast() {
        //Given
        log.info("Starting test: shouldFetchGetForecast");

        String city = "poznan";
        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(weatherClient.getWeatherForCity(city)).thenReturn(weatherDto);

        //When
        Optional<WeatherDto> weather = Optional.ofNullable(weatherClient.getWeatherForCity(city));

        //Then
        assertFalse(weather.isEmpty());
    }
}
