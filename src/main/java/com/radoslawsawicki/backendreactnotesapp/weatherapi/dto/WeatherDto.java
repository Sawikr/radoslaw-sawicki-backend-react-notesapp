package com.radoslawsawicki.backendreactnotesapp.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class WeatherDto {

    private float temperature;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private LocalDateTime createdAt;
}
