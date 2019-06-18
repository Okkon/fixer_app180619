package com.okkon.fixer_app180619;

import java.math.BigDecimal;
import java.util.Date;

public interface CurrencyCalculationService {
    BigDecimal calculateRevenue(int amount, Date purchaseDate);
}
