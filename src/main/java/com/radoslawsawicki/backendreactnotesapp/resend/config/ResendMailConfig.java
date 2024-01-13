package com.radoslawsawicki.backendreactnotesapp.resend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ResendMailConfig {

    @Value("${resend.apiKey}")
    private String apiKey;

    @Value("${resend.short.apiKey")
    private String shortApiKey;
}
