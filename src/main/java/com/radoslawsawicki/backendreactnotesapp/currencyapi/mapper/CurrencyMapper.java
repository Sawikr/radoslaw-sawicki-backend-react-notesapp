package com.radoslawsawicki.backendreactnotesapp.currencyapi.mapper;

import com.radoslawsawicki.backendreactnotesapp.currencyapi.domain.Currency;
import com.radoslawsawicki.backendreactnotesapp.currencyapi.dto.CurrencyDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CurrencyMapper {

    public Currency mapToCurrency(final CurrencyDto currencyDto) {
        return new Currency(
                currencyDto.getCode(),
                currencyDto.getCurrency(),
                currencyDto.getTable(),
                currencyDto.getRates()
        );
    }

    public CurrencyDto mapToCurrencyDto(final Currency currency) {
        return new CurrencyDto(
                currency.getCode(),
                currency.getCurrency(),
                currency.getTable(),
                currency.getRates()
        );
    }

    public List<CurrencyDto> mapToCurrencyDtoList(final List<Currency> currencyList) {
        return currencyList.stream()
                .map(this::mapToCurrencyDto)
                .toList();
    }
}
