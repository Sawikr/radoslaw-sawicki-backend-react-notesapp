package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.noteconfig.NoteServiceConfig;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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

	@GetMapping("/notes")
	public ResponseEntity<List<NoteDto>> getNotes(@RequestParam(required = false) Integer page,
												  @RequestParam(required = false) Sort.Direction sort) {
		int pageNumber = page == null || page < 0 ? 0 : page;
		Sort.Direction sortDirection = sort == null ? Sort.DEFAULT_DIRECTION : sort;
		List<Note> notes = service.getAllNotes(pageNumber, sortDirection);
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
		Note note = config.getNote(noteDto);
		note.setUpdatedAt(config.getCorrectDate());
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}

    @PatchMapping(value = "/notes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> updateNoteFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		service.updateNoteByFields(id, fields);
		return ResponseEntity.ok().build();
    }

	@PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {
		Note note = config.getNote(noteDto);
		note.setCreatedAt(config.getCorrectDate());
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}
}