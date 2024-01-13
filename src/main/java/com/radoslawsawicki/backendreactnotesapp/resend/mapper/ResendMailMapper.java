package com.radoslawsawicki.backendreactnotesapp.resend.mapper;

import com.radoslawsawicki.backendreactnotesapp.resend.domain.ResendMail;
import com.radoslawsawicki.backendreactnotesapp.resend.dto.ResendMailDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResendMailMapper {
    public ResendMail mapToResendMail(final ResendMailDto resendMailDto) {
        return new ResendMail(
                resendMailDto.getId(),
                resendMailDto.getEmailName()
        );
    }

    public ResendMailDto mapToResendMailDto(final ResendMail resendMail) {
        return new ResendMailDto(
                resendMail.getId(),
                resendMail.getEmailName()
        );
    }

    public List<ResendMailDto> mapToResendMailDtoList(final List<ResendMail> resendMailList) {
        return resendMailList.stream()
                .map(this::mapToResendMailDto)
                .toList();
    }
}
