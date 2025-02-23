package com.radoslawsawicki.backendreactnotesapp.security.config.sharesapi.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="DATES")
public class Data {

    private Long id;

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

    private Shares shares;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NonNull
    @Column(name = "OPEN")
    public float getOpen() {
        return open;
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
    @Column(name = "CLOSE")
    public float getClose() {
        return close;
    }

    @NonNull
    @Column(name = "VOLUME")
    public float getVolume() {
        return volume;
    }

    @NonNull
    @Column(name = "ADJ_HIGH")
    public float getAdj_high() {
        return adj_high;
    }

    @NonNull
    @Column(name = "ADJ_LOW")
    public float getAdj_low() {
        return adj_low;
    }

    @NonNull
    @Column(name = "ADJ_CLOSE")
    public float getAdj_close() {
        return adj_close;
    }

    @NonNull
    @Column(name = "ADJ_OPEN")
    public float getAdj_open() {
        return adj_open;
    }

    @NonNull
    @Column(name = "ADJ_VOLUME")
    public float getAdj_volume() {
        return adj_volume;
    }

    @NonNull
    @Column(name = "SPLIT_FACTOR")
    public float getSplit_factor() {
        return split_factor;
    }

    @NonNull
    @Column(name = "DIVIDEND")
    public float getDividend() {
        return dividend;
    }

    @NonNull
    @Column(name = "SYMBOL")
    public String getSymbol() {
        return symbol;
    }

    @NonNull
    @Column(name = "EXCHANGE")
    public String getExchange() {
        return exchange;
    }

    @NonNull
    @Column(name = "DATE")
    public String getDate() {
        return date;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "SHARES_ID")
    public Shares getShares() {
        return shares;
    }
}
