package com.radoslawsawicki.backendreactnotesapp.currencyapi.facade;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyProcessingException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyFacade.class);
    private final CurrencyService service;

    @Autowired
    public CurrencyFacade(CurrencyService service) {
        this.service = service;
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
}
