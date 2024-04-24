package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.noteconfig.NoteServiceConfig;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
		name = "CRUD REST APIs for note",
		description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE note"
)
public class NoteController {

	private final NoteMapper mapper;
	private final NoteService service;
    private final NoteServiceConfig config;

	@Operation(
			summary = "Fetch notes REST API",
			description = "REST API to fetch all notes with pagination"
	)
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
	@GetMapping("/notes")
	public ResponseEntity<List<NoteDto>> getNotes(@RequestParam(required = false) Integer page,
												  @RequestParam(required = false) Sort.Direction sort) {
		int pageNumber = page == null || page < 0 ? 0 : page;
		Sort.Direction sortDirection = sort == null ? Sort.DEFAULT_DIRECTION : sort;
		List<Note> notes = service.getAllNotes(pageNumber, sortDirection);
		return ResponseEntity.ok(mapper.mapToNoteDtoList(notes));
	}

	@Operation(
			summary = "Fetch a single note REST API",
			description = "REST API to a single note"
	)
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
	@GetMapping(value = "/notes/{id}")
	public ResponseEntity<NoteDto> getNote(@PathVariable Long id) throws NoteNotFoundException {
		return ResponseEntity.ok(mapper.mapToNoteDto(service.getNote(id)));
	}

	@Operation(
			summary = "Delete a single note REST API",
			description = "REST API to delete a single note"
	)
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
	@DeleteMapping(value = "/notes/{id}")
	public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
		service.deleteNote(id);
		return ResponseEntity.ok().build();
	}

	@Operation(
			summary = "Update a single note REST API",
			description = "REST API to update a single note"
	)
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
	@PutMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto) {
		Note note = config.getNote(noteDto);
		note.setUpdatedAt(config.getCorrectDate());
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}

	@Operation(
			summary = "Update note fields REST API",
			description = "REST API to update note fields"
	)
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @PatchMapping(value = "/notes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> updateNoteFields(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		service.updateNoteByFields(id, fields);
		return ResponseEntity.ok().build();
    }

	@Operation(
			summary = "Create note REST API",
			description = "REST API to create new note"
	)
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
	@PostMapping(value = "/notes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {
		Note note = config.getNote(noteDto);
		note.setCreatedAt(config.getCorrectDate());
		service.saveNote(note);
		return ResponseEntity.ok().build();
	}
}