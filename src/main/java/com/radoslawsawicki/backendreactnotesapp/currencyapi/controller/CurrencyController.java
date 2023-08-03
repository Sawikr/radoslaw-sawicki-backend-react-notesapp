package com.radoslawsawicki.backendreactnotesapp.currencyapi.controller;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.CurrencyProcessingException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.facade.CurrencyFacade;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.mapper.CurrencyMapper;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class CurrencyController {

    private final CurrencyService service;
    private final CurrencyMapper mapper;
    private final CurrencyFacade facade;

    @GetMapping("/currency/eur")
    public double getEURCurrency() throws CurrencyProcessingException {
        return facade.processGetCurrencyFromNbpApi("eur");
    }

    @GetMapping("/currency/usd")
    public double getUSDCurrency() throws CurrencyProcessingException {
        return facade.processGetCurrencyFromNbpApi("usd");
    }

    @GetMapping("/currency/chf")
    public double getCHFCurrency() throws CurrencyProcessingException {
        return facade.processGetCurrencyFromNbpApi("chf");
    }

    @GetMapping("/currency/gbp")
    public double getGBPCurrency() throws CurrencyProcessingException {
        return facade.processGetCurrencyFromNbpApi("gbp");
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyDto>> getCurrencyLists () {
        List<Currency> currencyLists = service.getAllCurrencies();
        return ResponseEntity.ok(mapper.mapToCurrencyDtoList(currencyLists));
    }

    @GetMapping(value = "/currency/{id}")
    public ResponseEntity<CurrencyDto> getCurrencyList(@PathVariable Long id) throws CurrencyNotFoundException {
        return ResponseEntity.ok(mapper.mapToCurrencyDto(service.getCurrency(id)));
    }

    @DeleteMapping(value = "/currency/{id}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long id) {
        service.deleteCurrency(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/currency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currencyList = mapper.mapToCurrency(currencyDto);
        service.saveCurrency(currencyList);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/currency", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCurrency(@RequestBody CurrencyDto currencyDto) {
        Currency currencyList = mapper.mapToCurrency(currencyDto);
        service.saveCurrency(currencyList);
        return ResponseEntity.ok().build();
    }
}