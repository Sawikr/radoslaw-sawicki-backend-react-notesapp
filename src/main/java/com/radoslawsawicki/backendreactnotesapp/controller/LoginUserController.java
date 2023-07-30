package com.radoslawsawicki.backendreactnotesapp.controller;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.dto.LoginUserDto;
import com.radoslawsawicki.backendreactnotesapp.exception.LoginUserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.LoginUserMapper;
import com.radoslawsawicki.backendreactnotesapp.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginUserController {

	private final LoginUserMapper mapper;
	private final LoginUserService service;

	@GetMapping("/users")
	public ResponseEntity<List<LoginUserDto>> getLoginUsers () {
		List<LoginUser> loginUsers = service.getAllLoginUsers();
		return ResponseEntity.ok(mapper.mapToNoteListDtoList(loginUsers));
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<LoginUserDto> getLoginUser(@PathVariable Long id) throws LoginUserNotFoundException {
		return ResponseEntity.ok(mapper.mapToLoginUserDto(service.getLoginUser(id)));
	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<Void> deleteLoginUser(@PathVariable Long id) {
		service.deleteLoginUser(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginUserDto> updateNote(@RequestBody LoginUserDto loginUserDto) {
		LoginUser loginUser = mapper.mapToLoginUser(loginUserDto);
		service.saveLoginUser(loginUser);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createNote(@RequestBody LoginUserDto loginUserDto) {
		LoginUser loginUser = mapper.mapToLoginUser(loginUserDto);
		service.saveLoginUser(loginUser);
		return ResponseEntity.ok().build();
	}
}










