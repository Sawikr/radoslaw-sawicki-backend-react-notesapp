package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.noteconfig.NoteServiceConfig;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteController {

	private final NoteMapper mapper;
	private final NoteService service;
    private final NoteServiceConfig config;

<<<<<<< HEAD
=======
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> 03b2c32 (Notes: updating)
	@GetMapping("/notes")
	public ResponseEntity<List<NoteDto>> getNotes () {
		List<Note> notes = service.getAllNotes();
		return ResponseEntity.ok(mapper.mapToNoteDtoList(notes));
	}

<<<<<<< HEAD
=======
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> 03b2c32 (Notes: updating)
	@GetMapping(value = "/notes/{id}")
	public ResponseEntity<NoteDto> getNote(@PathVariable Long id) throws NoteNotFoundException {
		return ResponseEntity.ok(mapper.mapToNoteDto(service.getNote(id)));
	}

<<<<<<< HEAD
=======
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> 03b2c32 (Notes: updating)
	@DeleteMapping(value = "/notes/{id}")
	public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
		service.deleteNote(id);
		return ResponseEntity.ok().build();
	}

<<<<<<< HEAD
=======
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> 03b2c32 (Notes: updating)
	@PutMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto) {
		Note note = config.getNote(noteDto);
		note.setUpdatedAt(config.getCorrectDate());
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}

<<<<<<< HEAD
    @PatchMapping(value = "/notes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> updateNoteFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		service.updateNoteByFields(id, fields);
		return ResponseEntity.ok().build();
    }

=======
	//@PreAuthorize("hasAnyRole('ADMIN','USER')")
>>>>>>> 03b2c32 (Notes: updating)
	@PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {
		Note note = config.getNote(noteDto);
		note.setCreatedAt(config.getCorrectDate());
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}
}