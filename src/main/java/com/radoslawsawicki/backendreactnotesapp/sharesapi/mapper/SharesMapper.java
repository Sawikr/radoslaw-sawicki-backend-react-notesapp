package com.radoslawsawicki.backendreactnotesapp.sharesapi.mapper;

import com.radoslawsawicki.backendreactnotesapp.sharesapi.dto.SharesDto;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.domain.Shares;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SharesMapper {

    public Shares mapToShares(final SharesDto sharesDto) {
        return new Shares(
                sharesDto.getPagination(),
                sharesDto.getData()
        );
    }

    public SharesDto mapToSharesDto(final Shares data) {
        return new SharesDto(
                data.getPagination(),
                data.getData()
        );
    }

    public List<SharesDto> mapToSharesDtoList(final List<Shares> dataList) {
        return dataList.stream()
                .map(this::mapToSharesDto)
                .toList();
    }
}
