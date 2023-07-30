package com.radoslawsawicki.backendreactnotesapp.weatherapi.controller;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.exception.WeatherNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.mapper.WeatherMapper;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class WeatherController {

    private final WeatherService service;
    private final WeatherMapper mapper;

    @GetMapping("/weather")
    public WeatherDto getWeather() {
        return service.getWeather();
    }

    @GetMapping("/weather/list")
    public ResponseEntity<List<WeatherDto>> getWeatherLists () {
        List<Weather> weatherLists = service.getAllWeathers();
        return ResponseEntity.ok(mapper.mapToWeatherDtoList(weatherLists));
    }

    @GetMapping(value = "/weather/{id}")
    public ResponseEntity<WeatherDto> getWeatherList(@PathVariable Long id) throws WeatherNotFoundException {
        return ResponseEntity.ok(mapper.mapToWeatherDto(service.getWeather(id)));
    }

    @DeleteMapping(value = "/weather/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable Long id) {
        service.deleteWeather(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherDto> updateNote(@RequestBody WeatherDto weatherDto) {
        Weather weatherList = mapper.mapToWeather(weatherDto);
        service.saveWeather(weatherList);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/weather", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createWeather(@RequestBody WeatherDto weatherDto) {
        Weather weatherList = mapper.mapToWeather(weatherDto);
        service.saveWeather(weatherList);
        return ResponseEntity.ok().build();
    }
}