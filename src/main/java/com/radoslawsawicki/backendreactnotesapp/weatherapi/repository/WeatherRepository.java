package com.radoslawsawicki.backendreactnotesapp.weatherapi.repository;

import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends CrudRepository<Weather, Long> {

    @Override
    List<Weather> findAll();

    @Override
    Optional<Weather> findById(Long id);

    @Override
    Weather save(Weather weather);

    @Override
    void deleteById(Long id);
}
