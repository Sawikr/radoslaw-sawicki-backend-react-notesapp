package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteListDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteListMapper;
import com.radoslawsawicki.backendreactnotesapp.service.NoteListService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(
		name = "CRUD REST APIs for a list of notes",
		description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE list of notes"
)
public class NoteListController {

	private final NoteListMapper mapper;
	private final NoteListService service;

	@GetMapping("/noteLists")
	public ResponseEntity<List<NoteListDto>> getNoteLists () {
		List<NoteList> noteLists = service.getAllNoteLists();
		return ResponseEntity.ok(mapper.mapToNoteListDtoList(noteLists));
	}

	@GetMapping(value = "/noteLists/{id}")
	public ResponseEntity<NoteListDto> getNoteList(@PathVariable Long id) throws NoteListNotFoundException {
		return ResponseEntity.ok(mapper.mapToNoteListDto(service.getNoteList(id)));
	}

	@DeleteMapping(value = "/noteLists/{id}")
	public ResponseEntity<Void> deleteNoteList(@PathVariable Long id) {
		service.deleteNoteList(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/noteLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteListDto> updateNoteList(@RequestBody NoteListDto noteListDto) {
		NoteList noteList = mapper.mapToNoteList(noteListDto);
		service.saveNoteList(noteList);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/noteLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createNoteList(@RequestBody NoteListDto noteListDto) {
		NoteList noteList = mapper.mapToNoteList(noteListDto);
		service.saveNoteList(noteList);
		return ResponseEntity.ok().build();
	}
}










