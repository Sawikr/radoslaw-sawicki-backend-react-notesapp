package com.radoslawsawicki.backendreactnotesapp.security.controller;

import com.radoslawsawicki.backendreactnotesapp.security.dto.LoginDto;
import com.radoslawsawicki.backendreactnotesapp.security.dto.RegisterDto;
import com.radoslawsawicki.backendreactnotesapp.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
