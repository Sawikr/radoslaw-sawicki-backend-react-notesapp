package com.radoslawsawicki.backendreactnotesapp.sharesapi.client;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.dto.SharesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SharesApiConfig {

    private final RestTemplate restTemplate;

    @Value("https://api.marketstack.com/v1/eod?access_key=ef499193cdf4486ef834e134767736db&limit=2&symbols=")
    private String sharesUrl;

    @Value("&date_from=")
    private String dateFrom;

    @Value("&date_to=")
    private String dateTo;

    LocalDate dateNov = LocalDate.now();
    LocalDate dateMinusDay = LocalDate.now().minusDays(5);

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
        return restTemplate.getForObject(sharesUrl + url + dateFrom + dateMinusDay + dateTo + dateTo,
                responseType, objects);
    }

}