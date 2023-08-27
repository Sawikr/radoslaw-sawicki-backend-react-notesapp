package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteListService {

    private final NoteListRepository repository;

    public List<NoteList> getAllNoteLists() {
        return repository.findAll();
    }

    public NoteList getNoteList(final Long noteListId) throws NoteListNotFoundException {
        return repository.findById(noteListId).orElseThrow(NoteListNotFoundException::new);
    }

    public NoteList getNoteList() throws NoteListNotFoundException {
        return repository.findById(repository.findAll().stream().findFirst().orElseThrow().getNoteListId()).orElseThrow(NoteListNotFoundException::new);
    }

    public NoteList saveNoteList(final NoteList noteList) {
        return repository.save(noteList);
    }

    public void deleteNoteList(final Long id) {
        repository.deleteById(id);
    }
}
