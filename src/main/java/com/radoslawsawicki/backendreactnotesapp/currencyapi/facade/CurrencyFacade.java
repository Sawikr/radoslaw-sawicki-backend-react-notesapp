package com.radoslawsawicki.backendreactnotesapp.currencyapi.facade;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.client.CurrencyClient;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyProcessingException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CurrencyFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyFacade.class);
    private final CurrencyService service;
    private final CurrencyClient client;

    @Autowired
    public CurrencyFacade(CurrencyService service, CurrencyClient client) {
        this.service = service;
        this.client = client;
    }

    public double processGetCurrencyFromNbpApi(String code) throws CurrencyProcessingException {
        double result = service.getCurrencyFromNbpApi(code);
        boolean wasError = false;
        if (result == 0) {
            LOGGER.error(CurrencyProcessingException.DATA_ERROR);
            wasError = true;
            throw new CurrencyProcessingException(CurrencyProcessingException.DATA_ERROR);
        }
        return result;
    }

    public CurrencyDto processGetCurrencyScheduler(String code) throws CurrencyProcessingException {
        Optional<CurrencyDto> result = Optional.ofNullable(client.getExchangeRates(code));
        boolean wasError = false;
        if (result.isEmpty()) {
            LOGGER.error(CurrencyProcessingException.DATA_ERROR);
            wasError = true;
            throw new CurrencyProcessingException(CurrencyProcessingException.DATA_ERROR);
        }
        return result.orElseThrow();
    }
}