package com.radoslawsawicki.backendreactnotesapp.currency;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Rate;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.repository.CurrencyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CurrencyTestSuite {

    @Autowired
    private CurrencyRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createCurrencyTest() {
        //Given
        List<Rate> rates = List.of(new Rate("161/A/NBP/2023", "2023-08-22", 4.4524));
        Currency currency = new Currency("A", "Euro", "eur", rates);

        //When
        repository.save(currency);

        //Then
        assertEquals(1, repository.count());
    }


    @Test
    void getAllCurrenciesTest() {
        //Given
        List<Rate> rates = List.of(new Rate("161/A/NBP/2023", "2023-08-22", 4.4524));
        Currency currency1 = new Currency("A", "Euro", "eur", rates);
        Currency currency2 = new Currency("A", "Euro", "eur", rates);

        //When
        repository.save(currency1);
        repository.save(currency2);

        //Then
        assertEquals(2, repository.findAll().size());
    }


    @Test
    void getCurrencyByIdTest() {

        //Given
        List<Rate> rates = List.of(new Rate("161/A/NBP/2023", "2023-08-22", 4.4524));
        Currency currency = new Currency("A", "Euro", "eur", rates);

        //When
        repository.save(currency);

        //Then
        assertTrue(repository.existsById(currency.getId()));
    }

    @Test
    void deleteCurrency() {
        //Given
        List<Rate> rates = List.of(new Rate("161/A/NBP/2023", "2023-08-22", 4.4524));
        Currency currency1 = new Currency("A", "Euro", "eur", rates);
        Currency currency2 = new Currency("A", "Euro", "eur", rates);

        //When
        repository.save(currency1);
        repository.save(currency2);
        repository.delete(currency1);

        //Then
        assertEquals(1, repository.count());
    }
}
