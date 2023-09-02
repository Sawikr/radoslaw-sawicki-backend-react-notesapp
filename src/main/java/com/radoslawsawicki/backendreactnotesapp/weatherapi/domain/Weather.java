package com.radoslawsawicki.backendreactnotesapp.weatherapi.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="WEATHERS")
public class Weather {

    private Long id;
    private float temperature;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private LocalDateTime createdAt;

    public Weather(float temperature, int pressure, int humidity, float windSpeed) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
    }

    public Weather(float temperature, int pressure, int humidity, float windSpeed, LocalDateTime createdAt) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "TEMPERATURE")
    public float getTemperature() {
        return temperature;
    }

    @NonNull
    @Column(name = "PRESSURE")
    public int getPressure() {
        return pressure;
    }

    @NonNull
    @Column(name = "HUMIDITY")
    public int getHumidity() {
        return humidity;
    }

    @NonNull
    @Column(name = "WINDSPEED")
    public float getWindSpeed() {
        return windSpeed;
    }

    @NonNull
    @Column(name = "CREATED_AT")
    @CreationTimestamp
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Float.compare(weather.temperature, temperature) == 0 && pressure == weather.pressure && humidity == weather.humidity && Float.compare(weather.windSpeed, windSpeed) == 0 && Objects.equals(id, weather.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, pressure, humidity, windSpeed);
    }
}
