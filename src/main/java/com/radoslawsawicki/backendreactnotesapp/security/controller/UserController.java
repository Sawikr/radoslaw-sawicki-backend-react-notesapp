package com.radoslawsawicki.backendreactnotesapp.security.controller;

import com.radoslawsawicki.backendreactnotesapp.security.domain.User;
import com.radoslawsawicki.backendreactnotesapp.security.dto.UserDto;
import com.radoslawsawicki.backendreactnotesapp.security.exception.UserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.security.mapper.UserMapper;
import com.radoslawsawicki.backendreactnotesapp.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private UserService service;
    private UserMapper mapper;

    @GetMapping("/auth/user")
    public ResponseEntity<List<UserDto>> getUsers () {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(mapper.mapToUserDtoList(users));
    }

    @GetMapping(value = "/auth/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(mapper.mapToUserDto(service.getUser(id)));
    }
}
