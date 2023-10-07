package com.radoslawsawicki.backendreactnotesapp.domain;

import com.radoslawsawicki.backendreactnotesapp.repository.NoteListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoteListTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NoteListTestSuite.class);

    @Autowired
    private NoteListRepository repository;

    @BeforeEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateNoteListTest() {
        //Given
        log.info("Starting test: shouldFetchCreateNoteListTest");

        NoteList noteList = new NoteList("TestList");

        //When
        repository.save(noteList);

        //Then
        assertEquals(1, repository.count());
    }

    @Test
    void shouldFetchGetAllNoteListsTest() {
        //Given
        log.info("Starting test: shouldFetchGetAllNoteListsTest");

        NoteList noteList1 = new NoteList("TestList");
        NoteList noteList2 = new NoteList("TestList");

        //When
        repository.save(noteList1);
        repository.save(noteList2);

        //Then
        assertEquals(2, repository.findAll().size());
    }

    @Test
    void shouldFetchGetNoteListByIdTest() {
        //Given
        log.info("Starting test: shouldFetchGetNoteListByIdTest");

        NoteList noteList = new NoteList("TestList");

        //When
        repository.save(noteList);

        //Then
        assertTrue(repository.existsById(noteList.getNoteListId()));
    }

    @Test
    void shouldFetchDeleteLoginUser() {
        //Given
        log.info("Starting test: shouldFetchDeleteLoginUser");

        NoteList noteList1 = new NoteList("TestList");
        NoteList noteList2 = new NoteList("TestList");

        //When
        repository.save(noteList1);
        repository.save(noteList2);
        repository.delete(noteList1);

        //Then
        assertEquals(1, repository.count());
    }
}

