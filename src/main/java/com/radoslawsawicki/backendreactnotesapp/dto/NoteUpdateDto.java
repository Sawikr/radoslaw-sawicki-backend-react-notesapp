package com.radoslawsawicki.backendreactnotesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class NoteUpdateDto {

    private Long id;
    private String title;
    private String body;
    private String category;
    private ZonedDateTime updatedAt;
}
