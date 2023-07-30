package com.radoslawsawicki.backendreactnotesapp.mapper;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteListDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoteListMapper {

    public NoteList mapToNoteList(final NoteListDto noteListDto) {
        return new NoteList(
                noteListDto.getListName()
        );
    }

    public NoteListDto mapToNoteListDto(final NoteList noteList) {
        return new NoteListDto(
                noteList.getNoteListId(),
                noteList.getListName()
        );
    }

    public List<NoteListDto> mapToNoteListDtoList(final List<NoteList> noteList) {
        return noteList.stream()
                .map(this::mapToNoteListDto)
                .toList();
    }
}
