package com.portfolio.financeportfolio.repository;

import com.portfolio.financeportfolio.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    //Query method to find by Ticker
    List<Price> findByTicker(String ticker);
}
