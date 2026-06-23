package com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.domain.Data;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.marketstack.domain.Pagination;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SharesDto {

    @JsonProperty("pagination")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Pagination pagination;

    @JsonProperty("data")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<Data> data;

}
