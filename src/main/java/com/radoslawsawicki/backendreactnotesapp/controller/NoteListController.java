package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteListDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteListMapper;
import com.radoslawsawicki.backendreactnotesapp.service.NoteListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteListController {

	private final NoteListMapper mapper;
	private final NoteListService service;

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/noteLists")
	public ResponseEntity<List<NoteListDto>> getNoteLists () {
		List<NoteList> noteLists = service.getAllNoteLists();
		return ResponseEntity.ok(mapper.mapToNoteListDtoList(noteLists));
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping(value = "/noteLists/{id}")
	public ResponseEntity<NoteListDto> getNoteList(@PathVariable Long id) throws NoteListNotFoundException {
		return ResponseEntity.ok(mapper.mapToNoteListDto(service.getNoteList(id)));
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@DeleteMapping(value = "/noteLists/{id}")
	public ResponseEntity<Void> deleteNoteList(@PathVariable Long id) {
		service.deleteNoteList(id);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PutMapping(value = "/noteLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteListDto> updateNoteList(@RequestBody NoteListDto noteListDto) {
		NoteList noteList = mapper.mapToNoteList(noteListDto);
		service.saveNoteList(noteList);
		return ResponseEntity.ok().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value = "/noteLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createNoteList(@RequestBody NoteListDto noteListDto) {
		NoteList noteList = mapper.mapToNoteList(noteListDto);
		service.saveNoteList(noteList);
		return ResponseEntity.ok().build();
	}
}










