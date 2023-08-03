package com.radoslawsawicki.backendreactnotesapp.weatherapi.service;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.client.WeatherClient;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.exception.WeatherNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;
    private final WeatherRepository repository;

    public WeatherDto getWeather() {
        return weatherClient.getWeatherForCity("poznan");
    }

    public List<Weather> getAllWeathers() {
        return repository.findAll();
    }

    public Weather getWeather(final Long noteId) throws WeatherNotFoundException {
        return repository.findById(noteId).orElseThrow(WeatherNotFoundException::new);
    }

    public Weather saveWeather(final Weather weather) {
        return repository.save(weather);
    }

    public void deleteWeather(final Long id) {
        repository.deleteById(id);
    }
}
