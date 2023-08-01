package com.radoslawsawicki.backendreactnotesapp.currencyapi.service;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.client.CurrencyClient;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyNotFoundException;
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

    public Currency getCurrency(final Long noteId) throws CurrencyNotFoundException {
        return repository.findById(noteId).orElseThrow(CurrencyNotFoundException::new);
    }

    public Currency saveCurrency(final Currency currency) {
        return repository.save(currency);
    }

    public void deleteCurrency(final Long id) {
        repository.deleteById(id);
    }

    public CurrencyDto getEURFromNbpApi() {
        return currencyClient.getExchangeRates("eur");
    }

    public CurrencyDto getUSDFromNbpApi() {
        return currencyClient.getExchangeRates("usd");
    }

    public CurrencyDto getCHFFromNbpApi() {
        return currencyClient.getExchangeRates("chf");
    }

    public CurrencyDto getGBPFromNbpApi() {
        return currencyClient.getExchangeRates("gbp");
    }
}
