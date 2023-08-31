package com.radoslawsawicki.backendreactnotesapp.currencyapi.client;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CurrencyClient {

    @Value("${currency_url.api.endpoint}")
    private String currencyUrl;

    @Value("${format_json.api.endpoint}")
    private String formatJson;

    private final RestTemplate restTemplate;

    public CurrencyDto getExchangeRates(String code) {
        CurrencyDto response = callGetMethod(code + formatJson,
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
        return restTemplate.getForObject(currencyUrl + url,
                responseType, objects);
    }
}
