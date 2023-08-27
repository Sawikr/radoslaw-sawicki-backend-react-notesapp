package com.radoslawsawicki.backendreactnotesapp.noteconfig;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.LoginUserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.service.LoginUserService;
import com.radoslawsawicki.backendreactnotesapp.service.NoteListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceConfig {

    private final NoteMapper mapper;
    private final LoginUserService loginUserService;
    private final NoteListService noteListService;

    public Note getNote(NoteDto noteDto) throws LoginUserNotFoundException, NoteListNotFoundException {
        Note note = mapper.mapToNote(noteDto);
        List<LoginUser> loginUserList = loginUserService.getAllLoginUsers();
        loginUser(note, loginUserList);
        List<NoteList> noteListList = noteListService.getAllNoteLists();
        noteList(note,noteListList);
        note.setUpdatedAt(getCorrectDate());
        return note;
    }

    private void loginUser(Note note, List<LoginUser> loginUserList) {
        LoginUser loginUser = loginUserList.stream().reduce((first, second) -> second).orElseThrow();
        note.setLoginUser(loginUser);
    }

    private void noteList(Note note, List<NoteList> noteListList) {
        NoteList noteList = noteListList.stream().reduce((first, second) -> second).orElseThrow();
        note.setNoteList(noteList);
    }

    public ZonedDateTime getCorrectDate() {
        LocalDateTime ldt = LocalDateTime.now(); 
        ZoneId warsaw = ZoneId.of("Europe/Warsaw"); 
        ZonedDateTime dateWarsaw = ZonedDateTime.of(ldt, warsaw);
        return dateWarsaw.plusHours(2);
    }
}
