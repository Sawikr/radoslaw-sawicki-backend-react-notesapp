package com.radoslawsawicki.backendreactnotesapp.currencyapi.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CURRENCIES")
public class Currency {

    private Long id;
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates = new ArrayList<>();

    public Currency(String table, String currency, String code, List<Rate> rates) {
        this.table = table;
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "TABLE_NAME")
    public String getTable() {
        return table;
    }

    @NonNull
    @Column(name = "CURRENCY_NAME")
    public String getCurrency() {
        return currency;
    }

    @NonNull
    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    @OneToMany(
            targetEntity = Rate.class,
            mappedBy = "currency",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    public List<Rate> getRates() {
        return rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(table, currency.table) && Objects.equals(this.currency, currency.currency) && Objects.equals(code, currency.code) && Objects.equals(rates, currency.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, table, currency, code, rates);
    }
}
