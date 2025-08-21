package com.portfolio.financeportfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "financialInstruments")
public class FinancialInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String letterCode;
    private String fullName;
    private String type;
    private BigDecimal currentPrice;

    public FinancialInstrument(String letterCode, String fullName, String type, BigDecimal currentPrice) {
        this.letterCode = letterCode;
        this.fullName = fullName;
        this.type = type;
        this.currentPrice = currentPrice;
    }
}
