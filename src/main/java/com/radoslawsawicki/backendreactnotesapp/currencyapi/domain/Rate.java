package com.radoslawsawicki.backendreactnotesapp.currencyapi.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="RATES")
public class Rate {

    private Long id;
    private String no;
    private String effectiveDate;
    private double mid;
    private Currency currency;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "NO")
    public String getNo() {
        return no;
    }

    @NonNull
    @Column(name = "EFFECTIVE_DATE")
    public String getEffectiveDate() {
        return effectiveDate;
    }

    @NonNull
    @Column(name = "MID")
    public double getMid() {
        return mid;
    }

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID")
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rate rate = (Rate) o;
        return Double.compare(rate.mid, mid) == 0 && Objects.equals(id, rate.id) && Objects.equals(no, rate.no) && Objects.equals(effectiveDate, rate.effectiveDate) && Objects.equals(currency, rate.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, no, effectiveDate, mid, currency);
    }
}
