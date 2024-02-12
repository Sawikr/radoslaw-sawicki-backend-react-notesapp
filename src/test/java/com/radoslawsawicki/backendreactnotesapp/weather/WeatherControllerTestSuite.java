package com.radoslawsawicki.backendreactnotesapp.weather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.radoslawsawicki.backendreactnotesapp.security.config.JwtTokenProvider;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.controller.WeatherController;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.domain.Weather;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.dto.WeatherDto;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.facade.WeatherFacade;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.mapper.WeatherMapper;
import com.radoslawsawicki.backendreactnotesapp.weatherapi.service.WeatherService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(WeatherController.class)
class WeatherControllerTestSuite {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeatherControllerTestSuite.class);

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
    private WeatherService service;

    @MockBean
    private WeatherFacade facade;

    @MockBean
    private WeatherMapper mapper;

    @Test
    void shouldFetchGetWeather() throws Exception {
        //Given
        log.info("Starting test: shouldFetchGetWeather");

        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(facade.processGetWeather()).thenReturn(Optional.of(weatherDto));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchGetWeather() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchGetWeather");

        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(facade.processGetWeather()).thenReturn(Optional.of(weatherDto));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/weather")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void shouldFetchGetWeatherLists() throws Exception {
        //Given
        log.info("Starting test: shouldFetchGetWeatherLists");

        Weather weather = new Weather(25.6F, 1005, 85, 1.5F, LocalDateTime.now());
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);

        when(service.getAllWeathers()).thenReturn(weatherList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/weather/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchGetWeatherLists() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchGetWeatherLists");

        Weather weather = new Weather(25.6F, 1005, 85, 1.5F, LocalDateTime.now());
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);

        when(service.getAllWeathers()).thenReturn(weatherList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/weather/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void shouldFetchGetWeatherList() throws Exception {
        //Given
        log.info("Starting test: shouldFetchGetWeatherList");

        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(service.getWeather()).thenReturn(weatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/weather/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldNotFetchGetWeatherList() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchGetWeatherList");

        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(service.getWeather()).thenReturn(weatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/notes/weather/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void shouldFetchDeleteWeather() throws Exception {
        //Given
        log.info("Starting test: shouldFetchDeleteWeather");

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            System.out.println("Called for id: " + id);
            assertEquals(1L, id);
            return null;
        }).when(service).deleteWeather(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/notes/weather/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(service, times(1)).deleteWeather(1L);
    }

    @Test
    void shouldNotFetchDeleteWeather() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchDeleteWeather");

        doAnswer(invocation -> {
            long id = invocation.getArgument(0);
            System.out.println("Called for id: " + id);
            assertEquals(1L, id);
            return null;
        }).when(service).deleteWeather(anyLong());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/notes/weather/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        verify(service, times(0)).deleteWeather(1L);
    }

    @Test
    void shouldFetchUpdateWeather() throws Exception {
        //Given
        log.info("Starting test: shouldFetchUpdateWeather");

        Weather weather = new Weather( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());
        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(service.saveWeather(mapper.mapToWeather(weatherDto))).thenReturn(weather);
        when(mapper.mapToWeather(weatherDto)).thenReturn(weather);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

        String jsonContent = gson.toJson(weatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldNotFetchUpdateWeather() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchUpdateWeather");

        Weather weather = new Weather( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());
        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(service.saveWeather(mapper.mapToWeather(weatherDto))).thenReturn(weather);
        when(mapper.mapToWeather(weatherDto)).thenReturn(weather);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();

        String jsonContent = gson.toJson(weatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void shouldFetchCreateWeather() throws Exception {
        //Given
        log.info("Starting test: shouldFetchCreateWeather");

        Weather weather = new Weather( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());
        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(service.saveWeather(mapper.mapToWeather(weatherDto))).thenReturn(weather);
        when(mapper.mapToWeatherDto(weather)).thenReturn(weatherDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        String jsonContent = gson.toJson(weatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER", "ADMIN"))
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void shouldNotFetchCreateWeather() throws Exception {
        //Given
        log.info("Starting test: shouldNotFetchCreateWeather");

        Weather weather = new Weather( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());
        WeatherDto weatherDto = new WeatherDto( 25.6F, 1005, 85, 1.5F, LocalDateTime.now());

        when(service.saveWeather(mapper.mapToWeather(weatherDto))).thenReturn(weather);
        when(mapper.mapToWeatherDto(weather)).thenReturn(weatherDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        String jsonContent = gson.toJson(weatherDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/notes/weather")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
