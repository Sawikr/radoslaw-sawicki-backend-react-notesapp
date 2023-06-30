package com.radoslawsawicki.backendreactnotesapp.dto;

import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoteDto {

    private Long id;
    private String title;
    private String body;
    private String category;
    private NoteList noteList;
}
