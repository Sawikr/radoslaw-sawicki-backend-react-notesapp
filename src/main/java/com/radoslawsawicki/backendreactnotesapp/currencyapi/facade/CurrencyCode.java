package com.radoslawsawicki.backendreactnotesapp.currencyapi.facade;

import lombok.Getter;

@Getter
public enum CurrencyCode {
    INSTANCE;

    public String setCurrency(String currencyName) {
        return new String(currencyName);
    }
}
