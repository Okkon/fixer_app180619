package com.okkon.fixer_app180619;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController()
@RequestMapping("/api")
public class CurrencyCalculationController {

    private final Logger log = LoggerFactory.getLogger(CurrencyCalculationController.class);

    @Autowired
    private CurrencyCalculationService calculationService;

    @GetMapping("/usd")
    public ResponseEntity<BigDecimal> toUsd(
            @RequestParam(value="amount") int amount,
            @RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date purchaseDate) {

        log.info("amount = ", amount);
        log.info("purchaseDate = ", purchaseDate);

        return ResponseEntity.ok(calculationService.calculateRevenue(amount, purchaseDate));
    }


}
