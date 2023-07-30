package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteListDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteListMapper;
import com.radoslawsawicki.backendreactnotesapp.service.NoteListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
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
	public ResponseEntity<Void> deleteLoginUser(@PathVariable Long id) {
		service.deleteNoteList(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/noteLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<NoteListDto> updateNote(@RequestBody NoteListDto noteListDto) {
		NoteList noteList = mapper.mapToNoteList(noteListDto);
		service.saveNoteList(noteList);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/noteLists", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createNote(@RequestBody NoteListDto noteListDto) {
		NoteList noteList = mapper.mapToNoteList(noteListDto);
		service.saveNoteList(noteList);
		return ResponseEntity.ok().build();
	}
}










