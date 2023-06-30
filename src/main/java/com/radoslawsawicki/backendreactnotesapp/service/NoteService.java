package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Note getNote(final Long noteId) throws NoteNotFoundException {
        return repository.findById(noteId).orElseThrow(NoteNotFoundException::new);
    }

    public Note saveNote(final Note note) {
        return repository.save(note);
    }

    public void deleteNote(final Long id) {
        repository.deleteById(id);
    }
}
