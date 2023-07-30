package com.radoslawsawicki.backendreactnotesapp.weatherapi.mapper;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WeatherMapper {

    public Weather mapToWeather(final WeatherDto weatherDto) {
        return new Weather(
                weatherDto.getTemperature(),
                weatherDto.getPressure(),
                weatherDto.getHumidity(),
                weatherDto.getWindSpeed()
        );
    }

    public WeatherDto mapToWeatherDto(final Weather weather) {
        return new WeatherDto(
                weather.getTemperature(),
                weather.getPressure(),
                weather.getHumidity(),
                weather.getWindSpeed()
        );
    }

    public List<WeatherDto> mapToWeatherDtoList(final List<Weather> weatherList) {
        return weatherList.stream()
                .map(this::mapToWeatherDto)
                .toList();
    }
}
