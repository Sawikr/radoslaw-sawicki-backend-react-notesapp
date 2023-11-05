package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.noteconfig.NoteServiceConfig;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;
    private final NoteServiceConfig config;

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

    public void updateNoteByFields(Long id, Map<String, Object> fields) {
        Optional<Note> existingNote = repository.findById(id);
        if (existingNote.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Note.class, key);
                Objects.requireNonNull(field).setAccessible(true);
                if (key.contains("updatedAt")) {
                    ZonedDateTime updatedAtValue = config.parseStringToZonedTimeDate(String.valueOf(value));
                    ReflectionUtils.setField(field, existingNote.get(), updatedAtValue);
                }
                else
                    ReflectionUtils.setField(field, existingNote.get(), value);
            });
            repository.save(existingNote.get());
        }
    }
}
