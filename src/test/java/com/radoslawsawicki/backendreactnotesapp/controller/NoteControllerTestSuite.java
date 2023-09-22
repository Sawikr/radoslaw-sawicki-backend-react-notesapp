package com.radoslawsawicki.backendreactnotesapp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.radoslawsawicki.backendreactnotesapp.domain.Note;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteMapper;
import com.radoslawsawicki.backendreactnotesapp.noteconfig.NoteServiceConfig;
import com.radoslawsawicki.backendreactnotesapp.service.NoteService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringJUnitWebConfig
@WebMvcTest(NoteController.class)
class NoteControllerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NoteControllerTestSuite.class);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        System.out.println("--------------------------");
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService service;

    @MockBean
    private NoteMapper mapper;

    @MockBean
    private NoteServiceConfig config;

    @Test
    void shouldFetchNotesList() throws Exception {
        //Given
        log.info("Starting test: shouldFetchNotesList");

        when(service.getAllNotes()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchGetNote() throws Exception, NoteNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetNote");

        Note note = getNote();
        NoteDto noteDto = getNoteDto();

        when((mapper.mapToNoteDto(note))).thenReturn(noteDto);
        when(service.getNote(note.getId())).thenReturn(note);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchDeleteNote() throws Exception {
        //Given
        log.info("Starting test: shouldFetchDeleteNote");

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            System.out.println("Called for id: " + id);
            assertEquals(1L, id);
            return null;
        }).when(service).deleteNote(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/notes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        verify(service, times(0)).deleteNote(1L);
    }

    @Test
    void shouldFetchUpdateNote() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUpdateNote");

        Note note = getNote();
        NoteDto noteDto = getNoteDto();

        when(mapper.mapToNoteDto(note)).thenReturn(noteDto);
        when(config.getNote(noteDto)).thenReturn(note);
        when(service.saveNote(note)).thenReturn(note);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
                .create();
        String jsonContent = gson.toJson(noteDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldFetchCreateNote() throws Exception {
        //Given
        log.info("Starting test: shouldFetchCreateNote");

        LocalDateTime ldt = LocalDateTime.now();
        ZoneId warsaw = ZoneId.of("Europe/Warsaw");
        ZonedDateTime dateWarsaw = ZonedDateTime.of(ldt, warsaw);

        Note note = getNote();
        NoteDto noteDto = getNoteDto();

        when(config.getNote(noteDto)).thenReturn(note);
        when(config.getCorrectDate()).thenReturn(dateWarsaw);
        when(config.getNote(noteDto)).thenReturn(note);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
                .create();
        String jsonContent = gson.toJson(noteDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    private Note getNote() {
        return new Note(1L, "Test", "Test note", "Programming",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
    }

    private NoteDto getNoteDto() {
        return new NoteDto(1L, "Test", "Test note", "Programming", "User",
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC),
                ZonedDateTime.of(LocalDate.now().atTime(11, 30), ZoneOffset.UTC));
    }
}
