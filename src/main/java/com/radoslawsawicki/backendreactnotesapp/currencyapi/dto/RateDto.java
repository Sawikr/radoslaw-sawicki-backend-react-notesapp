package com.radoslawsawicki.backendreactnotesapp.currencyapi.dto;

import com.fasterxml.jackson.annotation.*;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Rate;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDto {

    @JsonProperty("no")
    private String no;

    @JsonProperty("effectiveDate")
    private String effectiveDate;

    @JsonProperty("mid")
    private double mid;

    @JsonIgnore
    private List<Rate> additionalProperties = new ArrayList<>();
}
