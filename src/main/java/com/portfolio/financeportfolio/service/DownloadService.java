package com.portfolio.financeportfolio.service;

import com.portfolio.financeportfolio.entity.Price;
import com.portfolio.financeportfolio.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    private final PriceRepository priceRepository;

    public DownloadService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    private static final String STOOQ_URL = "https://stooq.pl/db/d/?d={date}&t=d";

    public List<Price> downloadAndSaveData(String date) throws IOException {
        String url = STOOQ_URL.replace("{date}", date);
        try (InputStream inputStream = new URL(url).openStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            List<Price> prices = reader.lines()
                    .skip(1) // skip header
                    .map(this::parseCsvLine)
                    .collect(Collectors.toList());

            // Save to H2
            priceRepository.saveAll(prices);

            return prices;
        }
    }

    //Parsing data
    private Price parseCsvLine(String line) {
        String[] parts = line.split(",");
        Price price = new Price();
        price.setTicker(parts[0]);
        price.setDate(LocalDate.parse(parts[2], DateTimeFormatter.BASIC_ISO_DATE));
        //price.setOpen(new BigDecimal(parts[4]));
        //price.setHigh(new BigDecimal(parts[5]));
        //price.setLow(new BigDecimal(parts[6]));
        price.setClose(new BigDecimal(parts[7]));
        //price.setVolume(Long.parseLong(parts[8]));
        return price;
    }
}