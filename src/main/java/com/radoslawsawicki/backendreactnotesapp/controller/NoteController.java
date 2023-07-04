package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

	private final NoteMapper mapper;
	private final NoteService service;

	@GetMapping("/")
	public ResponseEntity<List<NoteDto>> getNotes () {
		List<Note> notes = service.getAllNotes();
		return ResponseEntity.ok(mapper.mapToNoteDtoList(notes));
	}

	@GetMapping(value = "{noteId}")
	public ResponseEntity<NoteDto> getNote(@PathVariable Long noteId) throws NoteNotFoundException {
		return ResponseEntity.ok(mapper.mapToNoteDto(service.getNote(noteId)));
	}

	@DeleteMapping(value = "{noteId}")
	public ResponseEntity<Void> deleteNote(@PathVariable Long noteId) {
		service.deleteNote(noteId);
		return ResponseEntity.ok().build();
	}

	@PutMapping
	public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto) {
		Note note = mapper.mapToNote(noteDto);
		Note savedNote = service.saveNote(note);
		return ResponseEntity.ok(mapper.mapToNoteDto(savedNote));
	}

	@PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createNote(@RequestBody NoteDto noteDto) {
		Note note = mapper.mapToNote(noteDto);
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}
}










