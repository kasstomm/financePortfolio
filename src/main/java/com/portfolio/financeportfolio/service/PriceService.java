package com.portfolio.financeportfolio.service;

import com.portfolio.financeportfolio.entity.Price;
import com.portfolio.financeportfolio.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getAllBooks() {
        return priceRepository.findAll();
    }
}
