package com.radoslawsawicki.backendreactnotesapp.mapper;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteMapper {

    public Note mapToNote(final NoteDto noteDto) {
        return new Note(
                noteDto.getTitle(),
                noteDto.getBody(),
                noteDto.getCategory(),
                noteDto.getNoteList()
        );
    }

    public NoteDto mapToNoteDto(final Note note) {
        return new NoteDto(
                note.getId(),
                note.getTitle(),
                note.getBody(),
                note.getCategory(),
                note.getNoteList()
        );
    }

    public List<NoteDto> mapToNoteDtoList(final List<Note> noteList) {
        return noteList.stream()
                .map(this::mapToNoteDto)
                .toList();
    }
}
