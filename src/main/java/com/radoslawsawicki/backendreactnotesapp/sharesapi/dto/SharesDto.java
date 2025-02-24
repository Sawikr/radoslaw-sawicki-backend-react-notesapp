package com.radoslawsawicki.backendreactnotesapp.sharesapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.domain.Data;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.domain.Pagination;
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
