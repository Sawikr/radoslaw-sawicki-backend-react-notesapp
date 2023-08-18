package com.radoslawsawicki.backendreactnotesapp.mail.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MailConfig {

    @Value("${admin.mail}")
    private String adminMail;
}
