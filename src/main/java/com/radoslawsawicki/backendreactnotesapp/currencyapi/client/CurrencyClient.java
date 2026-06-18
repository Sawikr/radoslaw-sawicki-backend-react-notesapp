package com.radoslawsawicki.backendreactnotesapp.currencyapi.client;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@OpenAPIDefinition(
        info = @Info(
                title = "Important API Update: Transition to HTTPS.",
                description = "Only Effective August 1, 2025, all API communications must be conducted over HTTPS." +
                        "The HTTP protocol will no longer be supported for accessing our API services. " +
                        "This change is part of our ongoing commitment to enhancing security and protecting your data during transmission."
        ))
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
