package com.radoslawsawicki.backendreactnotesapp.domain;

import com.radoslawsawicki.backendreactnotesapp.repository.NoteListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class NoteListTestSuite {

    @Autowired
    private NoteListRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void createNoteListTest() {
        //Given
        NoteList noteList = new NoteList("TestList");

        //When
        repository.save(noteList);

        //Then
        assertEquals(1, repository.count());
    }


    @Test
    void getAllNoteListsTest() {
        //Given
        NoteList noteList1 = new NoteList("TestList");
        NoteList noteList2 = new NoteList("TestList");

        //When
        repository.save(noteList1);
        repository.save(noteList2);

        //Then
        assertEquals(2, repository.findAll().size());
    }


    @Test
    void getNoteListByIdTest() {

        //Given
        NoteList noteList = new NoteList("TestList");

        //When
        repository.save(noteList);

        //Then
        assertTrue(repository.existsById(noteList.getNoteListId()));
    }

    @Test
    void deleteLoginUser() {
        //Given
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

