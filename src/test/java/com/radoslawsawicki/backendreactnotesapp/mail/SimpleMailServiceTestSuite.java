package com.radoslawsawicki.backendreactnotesapp.mail;

import com.radoslawsawicki.backendreactnotesapp.mail.config.MailConfig;
import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import com.radoslawsawicki.backendreactnotesapp.mail.service.SimpleMailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleMailServiceTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SimpleMailServiceTestSuite.class);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.out.println("--------------------------");
    }

    @InjectMocks
    private SimpleMailService simpleMailService;

    @Mock
    private MailConfig config;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldFetchSendEmail() {
        //Given
        log.info("Starting test: shouldFetchSendEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        SimpleMailMessage mailMessage = getMailMessage(mail);

        //When
        simpleMailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldFetchSendEmailAndCheckTaskCounter() {
        //Given
        log.info("Starting test: shouldFetchSendEmailAndCheckTaskCounter");

        long size = 2;
        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        SimpleMailMessage mailMessage = getMailMessage(mail);

        //When
        simpleMailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
        assertEquals(2, size);
    }

    private SimpleMailMessage getMailMessage(Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(config.getAdminMail());
        mailMessage.setTo(mail.getEmail());
        mailMessage.setText(mail.getBody());
        mailMessage.setSubject(mail.getTitle());
        return mailMessage;
    }
}
