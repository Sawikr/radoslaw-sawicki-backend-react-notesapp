package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain.DataMassive;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SharesMassiveDto {

    @JsonProperty("dataMassive")
    @JsonIgnoreProperties(ignoreUnknown = true)
    private List<DataMassive> data;
}
