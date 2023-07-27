package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteListService {

    private final NoteListRepository repository;

    public NoteList saveNoteList(final NoteList noteList) {
        return repository.save(noteList);
    }

    public void deleteNoteList(final Long id) {
        repository.deleteById(id);
    }
}
