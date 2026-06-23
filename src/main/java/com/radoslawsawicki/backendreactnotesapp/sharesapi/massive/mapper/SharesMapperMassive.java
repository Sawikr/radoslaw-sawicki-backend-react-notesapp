package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.mapper;


import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain.SharesMassive;
import com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.dto.SharesMassiveDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SharesMapperMassive {

    public SharesMassive mapToShares(final SharesMassiveDto sharesMassiveDto) {
        return new SharesMassive(
                sharesMassiveDto.getData()
        );
    }

    public SharesMassiveDto mapToSharesDto(final SharesMassive data) {
        return new SharesMassiveDto(
                data.getData()
        );
    }

    public List<SharesMassiveDto> mapToSharesDtoList(final List<SharesMassive> dataList) {
        return dataList.stream()
                .map(this::mapToSharesDto)
                .toList();
    }
}
