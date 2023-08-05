package com.radoslawsawicki.backendreactnotesapp.scheduler;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Rate;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyProcessingException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.facade.CurrencyFacade;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.mapper.CurrencyMapper;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.CurrencyService;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.RateService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrencyScheduler {

    private static final String DESCRIPTION = "Currency rate download was successful!";
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyFacade.class);
    private final CurrencyFacade facade;
    private final CurrencyService currencyService;
    private final RateService rateService;
    private final CurrencyMapper mapper;
    private final Rate rate;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void getAndSaveCurrencyEUR() throws CurrencyProcessingException {
        CurrencyDto currencyScheduler = facade.processGetCurrencyScheduler("eur");
        Currency currency = mapper.mapToCurrency(currencyScheduler);
        currency.getRates().iterator().next().setCurrency(currency);
        rateService.saveRate(rate);
        currencyService.saveCurrency(currency);
        LOGGER.info(DESCRIPTION);
    }
}