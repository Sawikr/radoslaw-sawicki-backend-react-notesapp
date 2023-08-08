package com.radoslawsawicki.backendreactnotesapp.service;

import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.repository.NoteRepository;
import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.*;

@Service
@RequiredArgsConstructor
public class NoteServiceConfig {

    private final NoteService service;
    private final NoteMapper mapper;

    public Note getNote(NoteDto noteDto) {
        Note note = mapper.mapToNote(noteDto);
        LoginUser loginUser = new LoginUser("User", true);
		    note.setLoginUser(loginUser);
		    NoteList noteList = new NoteList("List");
		    note.setNoteList(noteList);
		    service.saveNote(note);
		    return note;
	   }

  public ZonedDateTime getCorrectDate(){
        LocalDateTime ldt = LocalDateTime.now(); 
        ZoneId warsaw = ZoneId.of("Europe/Warsaw"); 
        ZonedDateTime dateWarsaw = ZonedDateTime.of(ldt, warsaw);
        ZonedDateTime newDateWarsaw = dateWarsaw.plusHours(4);
        return newDateWarsaw;
  }
}
