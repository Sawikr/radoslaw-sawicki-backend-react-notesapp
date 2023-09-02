package com.radoslawsawicki.backendreactnotesapp.currency;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.controller.CurrencyController;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.facade.CurrencyFacade;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.mapper.CurrencyMapper;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.CurrencyService;
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
import static org.mockito.ArgumentMatchers.any;
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
    private CurrencyService service;

    @MockBean
    private CurrencyFacade facade;

    @MockBean
    private CurrencyMapper mapper;

    @Test
    void shouldFetchEURCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchEroCurrency");

        when(service.getCurrencyFromNbpApi("eur")).thenReturn(any(Double.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/eur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldFetchUSDCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUSDCurrency");

        when(service.getCurrencyFromNbpApi("usd")).thenReturn(any(Double.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldFetchCHFCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchCHFCurrency");

        when(service.getCurrencyFromNbpApi("chf")).thenReturn(any(Double.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldFetchGBPCurrency() throws Exception {
        //Given
        log.info("Starting test: shouldFetchGBPCurrency");

        when(service.getCurrencyFromNbpApi("gbp")).thenReturn(any(Double.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/currency/usd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
