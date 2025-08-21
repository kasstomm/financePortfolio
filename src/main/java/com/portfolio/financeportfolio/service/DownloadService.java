package com.portfolio.financeportfolio.service;

import com.portfolio.financeportfolio.entity.Price;
import com.portfolio.financeportfolio.repository.PriceRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DownloadService {

    private final PriceRepository priceRepository;

    public DownloadService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    private static final String DATA_PATH = "data/"; // relative to src/main/resources

    public List<Price> downloadAndSaveData(String date) throws IOException {
        String fileName = DATA_PATH + date + "_d.txt";

        // Load the file from resources
        ClassPathResource resource = new ClassPathResource(fileName);

        if (!resource.exists()) {
            throw new IOException("File not found: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

            List<Price> prices = reader.lines()
                    .skip(1) // skip header
                    .map(this::parseCsvLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Save to H2
            priceRepository.saveAll(prices);

            return prices;
        }
    }

    // Parsing CSV line
    private Price parseCsvLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null; // skip empty lines
        }

        String[] parts = line.split(",");
        if (parts.length < 8 || parts[2].isEmpty()) {
            System.out.println("Skipping invalid line: " + line);
            return null; // skip lines without enough data or empty date
        }

        Price price = new Price();
        price.setTicker(parts[0]);
        price.setDate(LocalDate.parse(parts[2], DateTimeFormatter.BASIC_ISO_DATE));
        // price.setOpen(new BigDecimal(parts[4]));
        // price.setHigh(new BigDecimal(parts[5]));
        // price.setLow(new BigDecimal(parts[6]));
        price.setClose(new BigDecimal(parts[7]));
        // price.setVolume(Long.parseLong(parts[8]));
        return price;
    }
}
