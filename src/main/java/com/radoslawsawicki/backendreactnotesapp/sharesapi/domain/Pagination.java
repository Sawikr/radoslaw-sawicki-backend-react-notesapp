package com.radoslawsawicki.backendreactnotesapp.sharesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Pagination {

    private int limit;

    private int offset;

    private int count;

    private int total;

}
