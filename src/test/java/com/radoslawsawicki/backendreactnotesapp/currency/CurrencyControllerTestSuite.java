package com.radoslawsawicki.backendreactnotesapp.currency;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.controller.CurrencyController;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.facade.CurrencyFacade;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.mapper.CurrencyMapper;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.CurrencyService;
import com.radoslawsawicki.backendreactnotesapp.security.config.JwtTokenProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(CurrencyController.class)
class CurrencyControllerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CurrencyControllerTestSuite.class);

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
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private CurrencyService service;

    @MockBean
    private CurrencyFacade facade;

    @MockBean
    private CurrencyMapper mapper;

    @Test
    void shouldFetchEURCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchEroCurrency");

        when(service.getCurrencyFromNbpApi("eur")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/eur")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchEURCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchEroCurrency");

        when(service.getCurrencyFromNbpApi("eur")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/eur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchUSDCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUSDCurrency");

        when(service.getCurrencyFromNbpApi("usd")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchUSDCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchUSDCurrency");

        when(service.getCurrencyFromNbpApi("usd")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchCHFCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchCHFCurrency");

        when(service.getCurrencyFromNbpApi("chf")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchCHFCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchCHFCurrency");

        when(service.getCurrencyFromNbpApi("chf")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void shouldFetchGBPCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchGBPCurrency");

        when(service.getCurrencyFromNbpApi("gbp")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchGBPCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchGBPCurrency");

        when(service.getCurrencyFromNbpApi("gbp")).thenReturn(4.0);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
