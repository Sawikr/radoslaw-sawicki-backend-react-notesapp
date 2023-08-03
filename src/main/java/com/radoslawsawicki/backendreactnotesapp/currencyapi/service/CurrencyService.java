package com.radoslawsawicki.backendreactnotesapp.currencyapi.service;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.client.CurrencyClient;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.facade.CurrencyCode;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;
    private final CurrencyRepository repository;

    public List<Currency> getAllCurrencies() {
        return repository.findAll();
    }

    public double getCurrencyFromNbpApi(String code) {
        CurrencyDto result = null;
        CurrencyCode currencyName = CurrencyCode.INSTANCE;
        if (currencyName.setCurrency("eur").equals(code)) {
            result = currencyClient.getExchangeRates(code);
        }
        else if (currencyName.setCurrency("usd").equals(code)) {
            result = currencyClient.getExchangeRates(code);
        }
        else if (currencyName.setCurrency("chf").equals(code)) {
            result = currencyClient.getExchangeRates(code);
        }
        else
            result = currencyClient.getExchangeRates(code);

        return result.getRates().get(0).getMid();
    }

    public Currency getCurrency(final Long noteId) throws CurrencyNotFoundException {
        return repository.findById(noteId).orElseThrow(CurrencyNotFoundException::new);
    }

    public void saveCurrency(final Currency currency) {
        repository.save(currency);
    }

    public void deleteCurrency(final Long id) {
        repository.deleteById(id);
    }
}
