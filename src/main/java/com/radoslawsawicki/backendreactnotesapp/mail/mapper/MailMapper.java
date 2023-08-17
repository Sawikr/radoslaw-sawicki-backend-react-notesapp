package com.radoslawsawicki.backendreactnotesapp.mail.mapper;

import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import com.radoslawsawicki.backendreactnotesapp.mail.dto.MailDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MailMapper {
    public Mail mapToMail(final MailDto mailDto) {
        return new Mail(
                mailDto.getId(),
                mailDto.getEmail(),
                mailDto.getTitle(),
                mailDto.getBody()
        );
    }

    public MailDto mapToMailDto(final Mail mail) {
        return new MailDto(
                mail.getId(),
                mail.getEmail(),
                mail.getTitle(),
                mail.getBody()
        );
    }

    public List<MailDto> mapToMailDtoList(final List<Mail> mailList) {
        return mailList.stream()
                .map(this::mapToMailDto)
                .toList();
    }
}
