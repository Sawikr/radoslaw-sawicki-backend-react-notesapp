package com.radoslawsawicki.backendreactnotesapp.sharesapi.massive.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DATES_MASSIVE")
public class DataMassive {

    private Long id;
    private float afterHours;
    private float close;
    private String from;
    private float high;
    private float low;
    private float open;
    private float preMarket;
    private String status;
    private String symbol;
    //private String date;
    private SharesMassive sharesMassive;

    public DataMassive(float afterHours, float close, String from, float high, float low, float open, float preMarket, String status, String symbol) {
        this.afterHours = afterHours;
        this.close = close;
        this.from = from;
        this.high = high;
        this.low = low;
        this.open = open;
        this.preMarket = preMarket;
        this.status = status;
        this.symbol = symbol;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "AFTER")
    public float getAfterHours() {
        return afterHours;
    }

    @NonNull
    @Column(name = "CLOSE")
    public float getClose() {
        return close;
    }

    //can't be just the FROM name - SQL error
    @NonNull
    @Column(name = "ADJ_FROM")
    public String getFrom() {
        return from;
    }

    @NonNull
    @Column(name = "HIGH")
    public float getHigh() {
        return high;
    }

    @NonNull
    @Column(name = "LOW")
    public float getLow() {
        return low;
    }

    @NonNull
    @Column(name = "OPEN")
    public float getOpen() {
        return open;
    }

    @NonNull
    @Column(name = "PREMARKET")
    public float getPreMarket() {
        return preMarket;
    }

    @NonNull
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    @NonNull
    @Column(name = "SYMBOL")
    public String getSymbol() {
        return symbol;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SHARES_MASSIVE_ID")
    public SharesMassive getSharesMassive() {
        return sharesMassive;
    }

}
