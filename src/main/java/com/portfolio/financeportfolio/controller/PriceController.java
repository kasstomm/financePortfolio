package com.portfolio.financeportfolio.controller;

import com.portfolio.financeportfolio.entity.Price;
import com.portfolio.financeportfolio.repository.PriceRepository;
import com.portfolio.financeportfolio.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceRepository priceRepository;
    private final DownloadService downloadService;

    public PriceController(PriceRepository priceRepository, DownloadService downloadService) {
        this.priceRepository = priceRepository;
        this.downloadService = downloadService;
    }

    // Endpoint to trigger download
    @PostMapping("/download")
    public String downloadData() throws IOException {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(today);
        downloadService.downloadAndSaveData(today);
        return "Data downloaded and saved!";
    }

    // Endpoint to get all prices
    @GetMapping
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    // Endpoint to get prices by ticker
    @GetMapping("/{ticker}")
    public List<Price> getPricesByTicker(@PathVariable String ticker) {
        return priceRepository.findByTicker(ticker);
    }
}
