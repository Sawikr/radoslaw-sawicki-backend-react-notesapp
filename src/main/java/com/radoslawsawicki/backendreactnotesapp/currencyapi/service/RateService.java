package com.radoslawsawicki.backendreactnotesapp.currencyapi.service;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Rate;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.exception.RateNotFoundException;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RateService {

    private final RateRepository repository;

    public Rate getRate(final Long noteId) throws RateNotFoundException {
        return repository.findById(noteId).orElseThrow(RateNotFoundException::new);
    }

    public void saveRate(final Rate rate) {
        repository.save(rate);
    }
}
