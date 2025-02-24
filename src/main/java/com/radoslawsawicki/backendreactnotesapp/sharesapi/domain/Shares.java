package com.radoslawsawicki.backendreactnotesapp.sharesapi.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="SHARES")
public class Shares {

    private Long id;

    private Pagination pagination;

    private List<Data> data = new ArrayList<>();

    public Shares(Pagination pagination, List<Data> data) {
        this.pagination = pagination;
        this.data = data;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToMany(
            targetEntity = Data.class,
            mappedBy = "shares",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    public List<Data> getData() {
        return data;
    }

    @OneToOne(
            targetEntity = Pagination.class,
            mappedBy = "shares",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    public Pagination getPagination() {
        return pagination;
    }
}
