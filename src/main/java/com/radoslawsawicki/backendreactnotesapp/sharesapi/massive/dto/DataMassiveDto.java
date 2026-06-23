package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class DataMassiveDto {

    @JsonProperty("afterHours")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private float afterHours;

    @JsonProperty("close")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private float close;

    @JsonProperty("from")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String from;

    @JsonProperty("high")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private float high;

    @JsonProperty("low")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private float low;

    @JsonProperty("open")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private float open;

    @JsonProperty("preMarket")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private float preMarket;

    @JsonProperty("status")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String status;

    @JsonProperty("symbol")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String symbol;
}
