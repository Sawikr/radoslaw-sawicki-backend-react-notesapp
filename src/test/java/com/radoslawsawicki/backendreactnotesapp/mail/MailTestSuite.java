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

    @Autowired
    private MailRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createEmailTest() {
        //Given
        Mail mail = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail);

        //Then
        assertEquals(1, repository.count());
    }


    @Test
    void getAllEmailsTest() {
        //Given
        Mail mail1 = new Mail("Test", "Test Message", "sawikr@op.pl");
        Mail mail2 = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail1 );
        repository.save(mail2 );

        //Then
        assertEquals(2, repository.findAll().size());
    }


    @Test
    void getEmailByIdTest() {

        //Given
        Mail mail = new Mail("Test", "Test Message", "sawikr@op.pl");

        //When
        repository.save(mail);

        //Then
        assertTrue(repository.existsById(mail.getId()));
    }

    @Test
    void deleteEmail() {
        //Given
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
