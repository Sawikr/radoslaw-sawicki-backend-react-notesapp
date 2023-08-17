package com.radoslawsawicki.backendreactnotesapp.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailDto {

    private Long id;
    private String email;
    private String title;
    private String body;
}
