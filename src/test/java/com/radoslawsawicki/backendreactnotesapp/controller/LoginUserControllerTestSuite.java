package com.radoslawsawicki.backendreactnotesapp.controller;

import com.google.gson.Gson;
import com.radoslawsawicki.backendreactnotesapp.domain.LoginUser;
import com.radoslawsawicki.backendreactnotesapp.dto.LoginUserDto;
import com.radoslawsawicki.backendreactnotesapp.exception.LoginUserNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.mapper.LoginUserMapper;
import com.radoslawsawicki.backendreactnotesapp.service.LoginUserService;
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
@WebMvcTest(LoginUserController.class)
class LoginUserControllerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoginUserControllerTestSuite.class);

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
    private LoginUserService service;

    @MockBean
    private LoginUserMapper mapper;

    @Test
    void shouldFetchLoginUsersList() throws Exception {
        //Given
        log.info("Starting test: shouldFetchLoginUsersList");

        when(service.getAllLoginUsers()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchGetLoginUser() throws Exception, LoginUserNotFoundException {
        //Given
        log.info("Starting test: shouldFetchGetLoginUser");

        LoginUser loginUser = new LoginUser("Test", true);
        LoginUserDto loginUserDto = new LoginUserDto(1L, "Test", true);

        when((mapper.mapToLoginUserDto(loginUser))).thenReturn(loginUserDto);
        when(service.getLoginUser(loginUserDto.getLoginUserId())).thenReturn(loginUser);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchDeleteLoginUser() throws Exception {
        //Given
        log.info("Starting test:  shouldFetchDeleteLoginUser");

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            System.out.println("Called for id: " + id);
            assertEquals(1L, id);
            return null;
        }).when(service).deleteLoginUser(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        verify(service, times(0)).deleteLoginUser(1L);
    }

    @Test
    void shouldFetchUpdateLoginUser() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUpdateLoginUser");

        LoginUser loginUser = new LoginUser("Test", true);
        LoginUserDto loginUserDto = new LoginUserDto(1L, "Test", true);

        when(mapper.mapToLoginUserDto(loginUser)).thenReturn(loginUserDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(loginUserDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void shouldFetchCreateLoginUser() throws Exception {
        //Given
        log.info("Starting test: shouldFetchCreateLoginUser");

        LoginUser loginUser = new LoginUser("Test", true);
        LoginUserDto loginUserDto = new LoginUserDto(1L, "Test", true);

        when(mapper.mapToLoginUser(loginUserDto)).thenReturn(loginUser);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(loginUserDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}

