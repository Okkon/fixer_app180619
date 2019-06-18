package com.okkon.fixer_app180619;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CurrencyCalculationServiceImpl implements CurrencyCalculationService {

    @Value("${my-fixer-key}")
    private String fixerApiKey;

    private double spread = 0.05;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public BigDecimal calculateRevenue(int amount, Date purchaseDate) {
        RestTemplate restTemplate = new RestTemplate();
        //TODO: добавить обработку ошибок
        FixerApiResponse pastRateResponse =
                restTemplate.getForObject(
                        String.format("http://data.fixer.io/api/%s?access_key=%s&symbols=USD,RUB",
                                format.format(purchaseDate),
                                fixerApiKey),
                        FixerApiResponse.class
                );

        FixerApiResponse currentRateResponse =
                restTemplate.getForObject(
                        String.format("http://data.fixer.io/api/latest?access_key=%s&symbols=USD,RUB",fixerApiKey),
                        FixerApiResponse.class
                );

        CurrencyRateResponse pastRates = pastRateResponse.getRates();
        BigDecimal pastUsdRate = BigDecimal.valueOf(pastRates.getUsdRate());
        BigDecimal pastRubRate = BigDecimal.valueOf(pastRates.getRubRate());
        BigDecimal pastUsdToRubRate = pastRubRate.divide(pastUsdRate, BigDecimal.ROUND_FLOOR);

        CurrencyRateResponse currentRates = currentRateResponse.getRates();
        BigDecimal currentUsdRate = BigDecimal.valueOf(currentRates.getUsdRate());
        BigDecimal currentRubRate = BigDecimal.valueOf(currentRates.getRubRate());
        BigDecimal currentUsdToRubRate = currentRubRate.divide(currentUsdRate, BigDecimal.ROUND_FLOOR);

        BigDecimal profit =
                currentUsdToRubRate.subtract(pastUsdToRubRate).subtract(BigDecimal.valueOf(spread))
                        .multiply(BigDecimal.valueOf(amount));
        //profit = (currentRate - pastRate - spread) * amount

        return profit;
    }

}
