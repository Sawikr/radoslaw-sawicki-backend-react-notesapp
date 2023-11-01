package com.radoslawsawicki.backendreactnotesapp.mapper;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteUpdateDto;
import org.springframework.stereotype.Service;

@Service
public class NoteUpdateMapper {

    public Note mapToNoteUpdate(final NoteUpdateDto noteUpdateDto) {
        return new Note(
                noteUpdateDto.getId(),
                noteUpdateDto.getTitle(),
                noteUpdateDto.getBody(),
                noteUpdateDto.getCategory(),
                noteUpdateDto.getUpdatedAt()
        );
    }
}
