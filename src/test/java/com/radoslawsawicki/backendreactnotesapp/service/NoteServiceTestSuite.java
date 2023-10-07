package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoteServiceTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NoteServiceTestSuite.class);

    @Autowired
    private NoteRepository repository;

    @Autowired
    private NoteService service;

    @BeforeEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateNoteTest() {
        //Given
        log.info("Starting test: shouldFetchCreateNoteTest");

        Note note = new Note("Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        service.saveNote(note);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void shouldFetchGetAllNotesTest() {
        //Given
        log.info("Starting test: shouldFetchGetAllNotesTest");

        Note note1 = new Note("Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
        Note note2 = new Note("Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note1);
        repository.save(note2);
        long size = service.getAllNotes().size();

        //Then
        assertEquals(2, size);
    }

    @Test
    void shouldFetchGetNoteByIdTest() throws NoteNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetNoteByIdTest");

        Note note = new Note("Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note);
        Optional<Note> noteId = Optional.ofNullable(service.getNote(note.getId()));

        //Then
        assertTrue(noteId.isPresent());
    }

    @Test
    void shouldFetchDeleteNote() {
        //Given
        log.info("Starting test: shouldFetchDeleteNote");

        Note note1 = new Note("Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
        Note note2 = new Note("Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note1);
        repository.save(note2);
        service.deleteNote(note1.getId());

        //Then
        assertEquals(1, repository.findAll().size());
    }
}
