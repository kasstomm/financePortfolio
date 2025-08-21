package com.portfolio.financeportfolio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private BigDecimal price;
    private String transactionType; //To powinno być enum
    private double volume;

    @ManyToOne
    @JoinColumn(name = "owned_instrument_id", referencedColumnName = "id")
    private OwnedInstrument ownedInstrument;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private UserPortfolio portfolio;
}
