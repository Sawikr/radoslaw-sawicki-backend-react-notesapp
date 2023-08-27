package com.radoslawsawicki.backendreactnotesapp.domain;

import com.radoslawsawicki.backendreactnotesapp.repository.NoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoteTestSuite {

    @Autowired
    private NoteRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createNoteTest() {
        //Given
        Note note = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void getAllNotesTest() {
        //Given
        Note note1 = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
        Note note2= new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note1);
        repository.save(note2);

        //Then
        assertEquals(2, repository.findAll().size());
    }


    @Test
    void getNoteByIdTest() {

        //Given
        Note note = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note);

        //Then
        assertTrue(repository.existsById(repository.findAll().stream().findFirst().orElseThrow().getId()));
    }

    @Test
    void deleteNote() {
        //Given
        Note note1 = new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
        Note note2= new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));

        //When
        repository.save(note1);
        repository.save(note2);
        repository.delete(repository.findAll().stream().findFirst().orElseThrow());

        //Then
        assertEquals(1, repository.count());
    }
}
