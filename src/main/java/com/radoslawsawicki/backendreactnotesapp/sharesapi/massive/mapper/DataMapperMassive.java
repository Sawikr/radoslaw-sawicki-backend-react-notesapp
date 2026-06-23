package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.mapper;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain.DataMassive;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto.DataMassiveDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DataMapperMassive {

    public DataMassive mapToData(final DataMassiveDto dataMassiveDto) {
        return new DataMassive(
                dataMassiveDto.getAfterHours(),
                dataMassiveDto.getClose(),
                dataMassiveDto.getFrom(),
                dataMassiveDto.getHigh(),
                dataMassiveDto.getLow(),
                dataMassiveDto.getOpen(),
                dataMassiveDto.getPreMarket(),
                dataMassiveDto.getSymbol(),
                dataMassiveDto.getSymbol()
        );
    }

    public DataMassiveDto mapToDataDto(final DataMassive data) {
        return new DataMassiveDto(
                data.getAfterHours(),
                data.getClose(),
                data.getFrom(),
                data.getHigh(),
                data.getLow(),
                data.getOpen(),
                data.getPreMarket(),
                data.getStatus(),
                data.getSymbol()
        );
    }

    public List<DataMassiveDto> mapToDataDtoList(final List<DataMassive> dataList) {
        return dataList.stream()
                .map(this::mapToDataDto)
                .toList();
    }
}
