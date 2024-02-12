package com.radoslawsawicki.backendreactnotesapp.domain;

import com.radoslawsawicki.backendreactnotesapp.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoteTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NoteTestSuite.class);

    @Autowired
    private NoteRepository repository;

    @BeforeEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateNoteTest() {
        //Given
        log.info("Starting test: shouldFetchCreateNoteTest");

        Note note = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void shouldFetchGetAllNotesTest() {
        //Given
        log.info("Starting test: shouldFetchGetAllNotesTest");

        Note note1 = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
        Note note2= new Note(2L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note1);
        repository.save(note2);

        //Then
        assertEquals(2, repository.findAll(Pageable.ofSize(2)).size());
    }

    @Test
    void shouldFetchGetNoteByIdTest() {
        //Given
        log.info("Starting test: shouldFetchGetNoteByIdTest");

        Note note = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note);

        //Then
        assertTrue(repository.existsById(repository.findAll(Pageable.ofSize(1)).stream().findFirst().orElseThrow().getId()));
    }

    @Test
    void shouldFetchDeleteNote() {
        //Given
        log.info("Starting test: shouldFetchDeleteNote");

        Note note1 = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
        Note note2= new Note(2L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note1);
        repository.save(note2);
        repository.delete(repository.findAll(Pageable.ofSize(1)).stream().findFirst().orElseThrow());

        //Then
        assertEquals(1, repository.count());
    }
}
