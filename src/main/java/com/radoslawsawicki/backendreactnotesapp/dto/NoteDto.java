package com.radoslawsawicki.backendreactnotesapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@Schema(
        name = "Note",
        description = "The schema to hold note information"
)
public class NoteDto {

    @Schema(
            name = "The note number in the database", example = "1"
    )
    private Long id;

    @Schema(
            name = "The name of the note", example = "First note"
    )
    private String title;

    @Schema(
            name = "The content of the created note", example = "The note contains information..."
    )
    private String body;

    @Schema(
            name = "The category of the created note", example = "Holidays"
    )
    private String category;

    @Schema(
            name = "The name of the logged in user", example = "User"
    )
    private String loginUser;

    @Schema(
            name = "The date the new note was created", type = "date-time", example = "2024-04-24T09:27:20.62Z"
    )
    private ZonedDateTime createdAt;

    @Schema(
            name = "The date the existing note was updated", type = "date-time", example = "2024-04-24T09:27:20.62Z"
    )
    private ZonedDateTime updatedAt;
}
