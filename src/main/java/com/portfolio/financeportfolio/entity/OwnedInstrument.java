package com.portfolio.financeportfolio.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "ownedInstruments")
public class OwnedInstrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "financial_instrument_id", referencedColumnName = "id")
    private FinancialInstrument financialInstrument;

    private double volume;

    /*@OneToMany(mappedBy = "ownedInstrument", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();*/

    @ManyToOne
    @JoinColumn(name = "portfolio_id", referencedColumnName = "id")
    private UserPortfolio portfolio;

}
