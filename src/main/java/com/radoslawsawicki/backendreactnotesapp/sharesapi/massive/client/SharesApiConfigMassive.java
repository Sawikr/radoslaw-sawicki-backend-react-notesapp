package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.client;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto.DataMassiveDto;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto.SharesMassiveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SharesApiConfigMassive {

    private final RestTemplate restTemplate;

    @Value("${shares_massive_url_.api.endpoint}")
    private String sharesUrl;

    @Value("${shares_massive_apikey}")
    private String apikey;

    LocalDate dateNov = LocalDate.now().minusDays(1);
    String symbolOne = "/";
    String symbolTwo = "?";
    String symbolThree = "apiKey=";

    //does not execute this code
    public SharesMassiveDto getShares(String code) {
        SharesMassiveDto response = callGetMethod(code,
                SharesMassiveDto.class,
                code);
        return SharesMassiveDto.builder()
                .data(response.getData())
                .build();
    }

    public DataMassiveDto getDates(String code) {
        DataMassiveDto response = callGetMethod(code,
                DataMassiveDto.class,
                code);
        return DataMassiveDto.builder()
                .afterHours(response.getAfterHours())
                .close(response.getClose())
                .from(response.getFrom())
                .high(response.getHigh())
                .low(response.getLow())
                .open(response.getOpen())
                .preMarket(response.getPreMarket())
                .status(response.getStatus())
                .symbol(response.getSymbol())
                .build();
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(sharesUrl + url + symbolOne + dateNov + symbolTwo + symbolThree + apikey,
                responseType, objects);
    }

}