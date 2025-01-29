package com.radoslawsawicki.backendreactnotesapp.sharesapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Shares {

    private Long id;

    private Pagination pagination;

    private List<Data> data = new ArrayList<>();

    public Shares(Pagination pagination, List<Data> data) {
        this.pagination = pagination;
        this.data = data;
    }
}
