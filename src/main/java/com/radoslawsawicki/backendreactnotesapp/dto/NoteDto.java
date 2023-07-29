package com.radoslawsawicki.backendreactnotesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
@AllArgsConstructor
public class NoteDto {

    private Long id;
    private String title;
    private String body;
    private String category;
    private Date createdAt;
    private Date updatedAt;
}
