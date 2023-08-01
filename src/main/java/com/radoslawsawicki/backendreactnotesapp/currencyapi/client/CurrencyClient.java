package com.radoslawsawicki.backendreactnotesapp.currencyapi.client;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyClient {

    private static final String CURRENCY_URL = "https://api.nbp.pl/api/exchangerates/rates/a/";
    private static final String FORMAT_JSON = "?format=json";
    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyDto getExchangeRates(String code) {
        CurrencyDto response = callGetMethod(code + FORMAT_JSON,
                CurrencyDto.class,
                code);
        return CurrencyDto.builder()
                .table(response.getTable())
                .currency(response.getCurrency())
                .code(response.getCode())
                .rates(response.getRates())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(CURRENCY_URL + url,
                responseType, objects);
    }
}
