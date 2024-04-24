package com.radoslawsawicki.backendreactnotesapp.mail.controller;

import com.radoslawsawicki.backendreactnotesapp.exception.MailNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import com.radoslawsawicki.backendreactnotesapp.mail.dto.MailDto;
import com.radoslawsawicki.backendreactnotesapp.mail.mapper.MailMapper;
import com.radoslawsawicki.backendreactnotesapp.mail.service.SimpleMailService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "CRUD REST APIs for the email",
        description = "CRUD REST APIs to CREATE, UPDATE and FETCH email"
)
public class MailController {

    private final SimpleMailService service;
    private final MailMapper mapper;

    @GetMapping("/notes/email")
    public ResponseEntity<List<MailDto>> getAllEmails() {
        List<Mail> emails = service.getAllEmails();
        return ResponseEntity.ok(mapper.mapToMailDtoList(emails));
    }

    @GetMapping(value = "/notes/email/{id}")
    public ResponseEntity<MailDto> getEmail(@PathVariable Long id) throws MailNotFoundException {
        return ResponseEntity.ok(mapper.mapToMailDto(service.getEmail(id)));
    }

    @PutMapping(value = "/notes/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailDto> updateEmail(@RequestBody MailDto mailDto) {
        service.saveEmail(mapper.mapToMail(mailDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/notes/email", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MailDto> sendEmail(@RequestBody MailDto mailDto) {
        service.saveEmail(mapper.mapToMail(mailDto));
        service.send(mapper.mapToMail(mailDto));
        return ResponseEntity.ok().build();
    }
}