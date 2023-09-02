package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteListServiceTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NoteListServiceTestSuite.class);

    @Autowired
    private NoteListRepository repository;

    @Autowired
    private NoteListService service;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void shouldFetchCreateNoteListTest() {
        //Given
        log.info("Starting test: shouldFetchCreateNoteListTest");

        NoteList noteList = new NoteList("TestList");

        // When
        service.saveNoteList(noteList);

        //Then
        assertTrue(repository.existsById(noteList.getNoteListId()));
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
        long size = service.getAllNoteLists().size();

        //Then
        assertEquals(2, size);
    }

    @Test
    void shouldFetchGetNoteListByIdTest() throws NoteListNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetNoteListByIdTest");

        NoteList noteList = new NoteList("TestList");

        // When
        repository.save(noteList);
        NoteList note = service.getNoteList(noteList.getNoteListId());

        //Then
        assertFalse(note.getListName().isEmpty());
    }

    @Test
    void shouldFetchDeleteNoteList() {
        //Given
        log.info("Starting test: shouldFetchDeleteNoteList");

        NoteList noteList1 = new NoteList("TestList");
        NoteList noteList2 = new NoteList("TestList");

        //When
        repository.save(noteList1);
        repository.save(noteList2);
        service.deleteNoteList(noteList1.getNoteListId());

        //Then
        assertEquals(1, repository.findAll().size());
    }
}

