package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.service.LoginUserService;
import com.radoslawsawicki.backendreactnotesapp.service.NoteListService;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteController {

	private final NoteMapper mapper;
	private final NoteService service;
	private final NoteListService noteListService;
	private final LoginUserService loginUserService;

	@GetMapping("/notes")
	public ResponseEntity<List<NoteDto>> getNotes () {
		List<Note> notes = service.getAllNotes();
		return ResponseEntity.ok(mapper.mapToNoteDtoList(notes));
	}

	@GetMapping(value = "/notes/{id}")
	public ResponseEntity<NoteDto> getNote(@PathVariable Long id) throws NoteNotFoundException {
		return ResponseEntity.ok(mapper.mapToNoteDto(service.getNote(id)));
	}

	@DeleteMapping(value = "/notes/{id}")
	public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
		service.deleteNote(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto) {
		Note note = mapper.mapToNote(noteDto);
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createNote(@RequestBody NoteDto noteDto) {
		Note note = mapper.mapToNote(noteDto);
		LoginUser loginUser = new LoginUser("User", true);
		note.setLoginUser(loginUser);
		NoteList noteList = new NoteList("List");
		note.setNoteList(noteList);
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}
}










