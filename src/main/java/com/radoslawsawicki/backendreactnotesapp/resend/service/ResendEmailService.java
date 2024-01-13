package com.radoslawsawicki.backendreactnotesapp.resend.service;

import com.radoslawsawicki.backendreactnotesapp.resend.config.ResendMailConfig;
import com.radoslawsawicki.backendreactnotesapp.resend.domain.ResendMail;
import com.radoslawsawicki.backendreactnotesapp.resend.repository.ResendRepository;
import com.radoslawsawicki.backendreactnotesapp.resend.template.EmailTemplate;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResendEmailService {

    private final ResendRepository repository;
    private final ResendMailConfig config;
    private final EmailTemplate email;

    public void send(final ResendMail mail) {
        Resend resend = new Resend(config.getApiKey());

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("NotesApp <contact@notesapp.pl>")
                .to(mail.getEmailName())
                .html(email.emailTemplate(mail))
                .subject("Reset password of " + mail.getEmailName())
                .headers(Map.of(
                        "X-Entity-Ref-ID", config.getShortApiKey()
                ))
                .build();

        try {
            SendEmailResponse data = resend.emails().send(sendEmailRequest);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }

    public List<ResendMail> getAllEmails() {
        return repository.findAll();
    }
}