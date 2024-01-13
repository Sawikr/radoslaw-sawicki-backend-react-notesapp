package com.radoslawsawicki.backendreactnotesapp.resend.controller;

import com.radoslawsawicki.backendreactnotesapp.resend.domain.ResendMail;
import com.radoslawsawicki.backendreactnotesapp.resend.dto.ResendMailDto;
import com.radoslawsawicki.backendreactnotesapp.resend.mapper.ResendMailMapper;
import com.radoslawsawicki.backendreactnotesapp.resend.service.ResendEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@EnableAspectJAutoProxy
@RequestMapping("/api")
public class ResendMailController {

    private final ResendEmailService service;
    private final ResendMailMapper mapper;

    @GetMapping("/notes/resend")
    public ResponseEntity<List<ResendMailDto>> getAllEmails() {
        List<ResendMail> emails = service.getAllEmails();
        return ResponseEntity.ok(mapper.mapToResendMailDtoList(emails));
    }

    @PostMapping(value = "/notes/resend", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResendMailDto> sendResendEmail(@RequestBody ResendMailDto resendMailDto) {
        service.send(mapper.mapToResendMail(resendMailDto));
        return ResponseEntity.ok().build();
    }
}