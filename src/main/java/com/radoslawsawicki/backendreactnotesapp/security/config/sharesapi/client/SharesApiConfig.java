package com.radoslawsawicki.backendreactnotesapp.security.config.sharesapi.client;

import com.radoslawsawicki.backendreactnotesapp.security.config.sharesapi.dto.SharesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SharesApiConfig {

    private final RestTemplate restTemplate;

    @Value("${shares_url.api.endpoint}")
    private String sharesUrl;

    @Value("${shares_date_from}")
    private String dateFrom;

    @Value("${shares_date_to}")
    private String dateTo;

    LocalDate dateNov = LocalDate.now();
    LocalDate dateMinusDay = LocalDate.now().minusDays(3);

    public SharesDto getShares(String code) {
        SharesDto response = callGetMethod(code,
                SharesDto.class,
                code);
        return SharesDto.builder()
                .pagination(response.getPagination())
                .data(response.getData())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(sharesUrl + url + dateFrom + dateMinusDay + dateTo + dateNov,
                responseType, objects);
    }

}