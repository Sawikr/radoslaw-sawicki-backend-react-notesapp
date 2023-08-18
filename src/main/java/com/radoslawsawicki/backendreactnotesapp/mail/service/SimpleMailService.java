package com.radoslawsawicki.backendreactnotesapp.mail.service;

import com.radoslawsawicki.backendreactnotesapp.exception.MailNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mail.config.MailConfig;
import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import com.radoslawsawicki.backendreactnotesapp.mail.repository.MailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMailService {

    private final JavaMailSender javaMailSender;
    private final MailRepository repository;
    private final MailConfig config;

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    public List<Mail> getAllEmails() {
        return repository.findAll();
    }

    public Mail getEmail(final Long emailId) throws MailNotFoundException {
        return repository.findById(emailId).orElseThrow(MailNotFoundException::new);
    }

    public void saveEmail(final Mail mail) {
        repository.save(mail);
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(config.getAdminMail());
        mailMessage.setTo(mail.getEmail());
        mailMessage.setText(mail.getBody());
        mailMessage.setSubject(mail.getTitle());
        return mailMessage;
    }
}