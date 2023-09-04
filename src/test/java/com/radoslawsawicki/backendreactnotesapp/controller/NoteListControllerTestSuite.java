package com.radoslawsawicki.backendreactnotesapp.controller;

import com.google.gson.Gson;
import com.radoslawsawicki.backendreactnotesapp.domain.NoteList;
import com.radoslawsawicki.backendreactnotesapp.dto.NoteListDto;
import com.radoslawsawicki.backendreactnotesapp.exception.NoteListNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.NoteListMapper;
import com.radoslawsawicki.backendreactnotesapp.service.NoteListService;
import org.hamcrest.Matchers;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringJUnitWebConfig
@WebMvcTest(NoteListController.class)
class NoteListControllerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NoteListControllerTestSuite.class);

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
    private NoteListService service;

    @MockBean
    private NoteListMapper mapper;

    @Test
    void shouldFetchNoteLists() throws Exception {
        //Given
        log.info("Starting test: shouldFetchNoteLists");

        when(service.getAllNoteLists()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/noteLists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldFetchGetNoteList() throws Exception, NoteListNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetNoteList");

        NoteList noteList = new NoteList(1L, "TestList");
        NoteListDto noteListDto = new NoteListDto(1L, "TestList");

        when((mapper.mapToNoteListDto(noteList))).thenReturn(noteListDto);
        when(service.getNoteList(noteList.getNoteListId())).thenReturn(noteList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/noteLists/1")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$.noteListId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.listName", Matchers.is("TestList")));
    }

    @Test
    void shouldFetchDeleteNoteList() throws Exception {
        //Given
        log.info("Starting test: shouldFetchDeleteNoteList");

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            System.out.println("Called for id: " + id);
            assertEquals(1L, id);
            return null;
        }).when(service).deleteNoteList(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/noteLists/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service, times(1)).deleteNoteList(1L);
    }

    @Test
    void shouldFetchUpdateNoteList() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUpdateNoteList");

        NoteList noteList = new NoteList(1L, "TestList");
        NoteListDto noteListDto = new NoteListDto(1L, "TestList");

        when(mapper.mapToNoteListDto(noteList)).thenReturn(noteListDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(noteListDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/noteLists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldFetchCreateNoteList() throws Exception {
        //Given
        log.info("Starting test:  shouldFetchCreateNoteList");

        NoteList noteList = new NoteList(1L, "TestList");
        NoteListDto noteListDto = new NoteListDto(1L, "TestList");

        when(mapper.mapToNoteList(noteListDto)).thenReturn(noteList);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(noteListDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/noteLists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

