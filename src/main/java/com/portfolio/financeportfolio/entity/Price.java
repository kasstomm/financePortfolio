package com.portfolio.financeportfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticker;

    private LocalDate date;

    //private BigDecimal open;
    //private BigDecimal high;
    //private BigDecimal low;
    private BigDecimal close;
    //private Long volume;

    public Price(String ticker, LocalDate date, BigDecimal close) {
        this.ticker = ticker;
        this.date = date;
        this.close = close;
    }
}