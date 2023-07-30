package com.radoslawsawicki.backendreactnotesapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoteListDto {

    private Long noteListId;
    private String listName;
}
