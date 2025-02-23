package com.radoslawsawicki.backendreactnotesapp.security.config.sharesapi.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PAGINATION'S")
public class Pagination {

    private Long id;

    private int limit;

    private int offset;

    private int count;

    private int total;

    private Shares shares;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "LIMIT'S")
    public int getLimit() {
        return limit;
    }

    @NonNull
    @Column(name = "OFFSET'S")
    public int getOffset() {
        return offset;
    }

    @NonNull
    @Column(name = "COUNT'S")
    public int getCount() {
        return count;
    }

    @NonNull
    @Column(name = "TOTAL'S")
    public int getTotal() {
        return total;
    }

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SHARES_ID")
    public Shares getShares() {
        return shares;
    }
}
