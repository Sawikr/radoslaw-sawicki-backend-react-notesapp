package com.radoslawsawicki.backendreactnotesapp.currencyapi.repository;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {

    @Override
    Optional<Rate> findById(Long id);

    @Override
    Rate save(Rate rate);
}
