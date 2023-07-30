package com.radoslawsawicki.backendreactnotesapp.weatherapi.client;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.OpenWeatherWeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "40b75ad81413da557861dbb8414a394e";
    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherDto getWeatherForCity(String city) {
        OpenWeatherWeatherDto openWeatherWeatherDto = callGetMethod("weather?q={city}&appid={apiKey}&units=metric&lang=pl",
                OpenWeatherWeatherDto.class,
                city, API_KEY);
        return WeatherDto.builder()
                .temperature(openWeatherWeatherDto.getMain().getTemp())
                .pressure(openWeatherWeatherDto.getMain().getPressure())
                .humidity(openWeatherWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherWeatherDto.getWind().getSpeed())
                .build();
    }

    public String getForecast(double lat, double lon) {
        return callGetMethod("onecall?lat={lat}&lon={lon}&exclude=minutely,hourly&appid={apiKey}&units=metric&lang=pl",
                String.class,
                lat, lon, API_KEY);
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(WEATHER_URL + url,
                responseType, objects);
    }
}
