package com.radoslawsawicki.backendreactnotesapp.weatherapi.client;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.OpenWeatherWeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    @Value("${weather_url.api.endpoint}")
    private String weatherUrlApi;

    @Value("${weather_api_key.api.endpoint}")
    private String weatherApiKey;

    @Value("${weather_url_get_weather_for_city.api.endpoint}")
    private String weatherUrlApiGetWeatherForCity;

    @Value("${weather_url_get_forecast.api.endpoint}")
    private String weatherUrlApiGetForecast;

    private final RestTemplate restTemplate;

    public WeatherDto getWeatherForCity(String city) {
        OpenWeatherWeatherDto openWeatherWeatherDto = callGetMethod(weatherUrlApiGetWeatherForCity,
                OpenWeatherWeatherDto.class,
                city, weatherApiKey);
        return WeatherDto.builder()
                .temperature(openWeatherWeatherDto.getMain().getTemp())
                .pressure(openWeatherWeatherDto.getMain().getPressure())
                .humidity(openWeatherWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherWeatherDto.getWind().getSpeed())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public String getForecast(double lat, double lon) {
        return callGetMethod(weatherUrlApiGetForecast,
                String.class,
                lat, lon, weatherApiKey);
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(weatherUrlApi + url,
                responseType, objects);
    }
}
