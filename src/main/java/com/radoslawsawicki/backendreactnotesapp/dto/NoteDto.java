package com.radoslawsawicki.backendreactnotesapp.dto;

import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
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
    private Date updatedAt;
    private NoteList noteList;
    private LoginUser loginUser;
}
