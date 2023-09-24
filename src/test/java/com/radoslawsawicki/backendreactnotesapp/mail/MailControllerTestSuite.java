package com.radoslawsawicki.backendreactnotesapp.mail;

import com.google.gson.Gson;
import com.radoslawsawicki.backendreactnotesapp.exception.MailNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mail.controller.MailController;
import com.radoslawsawicki.backendreactnotesapp.mail.domain.Mail;
import com.radoslawsawicki.backendreactnotesapp.mail.dto.MailDto;
import com.radoslawsawicki.backendreactnotesapp.mail.mapper.MailMapper;
import com.radoslawsawicki.backendreactnotesapp.mail.service.SimpleMailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(MailController.class)
class MailControllerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MailControllerTestSuite.class);

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
    SimpleMailService service;

    @MockBean
    private MailMapper mapper;

    @Test
    void shouldFetchEmailList() throws Exception {
        //Given
        log.info("Starting test: shouldFetchEmailList");

        when(service.getAllEmails()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchEmailList() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchEmailList");

        when(service.getAllEmails()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/email")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchGetEmail() throws Exception, MailNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");
        MailDto mailDto = new MailDto(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        when((mapper.mapToMailDto(mail))).thenReturn(mailDto);
        when(service.getEmail(mail.getId())).thenReturn(mail);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/email/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchGetEmail() throws Exception, MailNotFoundException {
        //Given
        log.info("Starting test: shouldNotFetchGetEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");
        MailDto mailDto = new MailDto(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        when((mapper.mapToMailDto(mail))).thenReturn(mailDto);
        when(service.getEmail(mail.getId())).thenReturn(mail);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/email/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchUpdateEmail() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUpdateEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");
        MailDto mailDto = new MailDto(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        when(mapper.mapToMailDto(mail)).thenReturn(mailDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(mailDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchUpdateEmail() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchUpdateEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");
        MailDto mailDto = new MailDto(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        when(mapper.mapToMailDto(mail)).thenReturn(mailDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(mailDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldFetchCreateEmail() throws Exception {
        //Given
        log.info("Starting test: shouldFetchCreateEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");
        MailDto mailDto = new MailDto(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        when(mapper.mapToMail(mailDto)).thenReturn(mail);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(mailDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notes/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchCreateEmail() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchCreateEmail");

        Mail mail = new Mail(1L, "sawikr10@gmail.com", "Test",
                "Test message");
        MailDto mailDto = new MailDto(1L, "sawikr10@gmail.com", "Test",
                "Test message");

        when(mapper.mapToMail(mailDto)).thenReturn(mail);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(mailDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/notes/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
