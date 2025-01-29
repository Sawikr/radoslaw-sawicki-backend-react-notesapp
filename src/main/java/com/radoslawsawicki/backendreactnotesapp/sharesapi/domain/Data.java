package com.radoslawsawicki.backendreactnotesapp.sharesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Data {

    private float open;

    private float high;

    private float low;

    private float close;

    private float volume;

    private float adj_high;

    private float adj_low;

    private float adj_close;

    private float adj_open;

    private float adj_volume;

    private float split_factor;

    private float dividend;

    private String symbol;

    private String exchange;

    private String date;

}
