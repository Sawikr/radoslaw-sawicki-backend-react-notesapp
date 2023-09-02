package com.radoslawsawicki.backendreactnotesapp.mail;

import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import com.radoslawsawicki.backendreactnotesapp.mail.repository.MailRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MailTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MailTestSuite.class);

    @Autowired
    private MailRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateEmailTest() {
        //Given
        log.info("Starting test: shouldFetchCreateEmailTest");

        Mail mail = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void shouldFetchGetAllEmailsTest() {
        //Given
        log.info("Starting test: shouldFetchGetAllEmailsTest");

        Mail mail1 = new Mail("Test", "Test Message", "sawikr@op.pl");
        Mail mail2 = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail1 );
        repository.save(mail2 );

        //Then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldFetchGetEmailByIdTest() {
        //Given
        log.info("Starting test: shouldFetchGetEmailByIdTest");

        Mail mail = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail);

        //Then
        assertTrue(repository.existsById(mail.getId()));
    }

    @Test
    void shouldFetchDeleteEmail() {
        //Given
        log.info("Starting test: shouldFetchDeleteEmail");

        Mail mail1 = new Mail("Test", "Test Message", "sawikr@op.pl");
        Mail mail2 = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail1 );
        repository.save(mail2 );
        repository.delete(mail1);

        //Then
        assertEquals(1, repository.count());
    }
}
