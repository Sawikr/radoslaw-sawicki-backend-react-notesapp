package com.radoslawsawicki.backendreactnotesapp.currencyapi.repository;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    @Override
    List<Currency> findAll();

    @Override
    Optional<Currency> findById(Long id);

    @Override
    Currency save(Currency currency);

    @Override
    void deleteById(Long id);
}
