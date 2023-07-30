package com.radoslawsawicki.backendreactnotesapp.weatherapi.dto;

import lombok.Getter;

@Getter
public class OpenWeatherWeatherDto {

    private OpenWeatherMainDto main;
    private OpenWeatherWindDto wind;
}
