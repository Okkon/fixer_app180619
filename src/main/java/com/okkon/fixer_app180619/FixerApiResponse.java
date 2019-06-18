package com.okkon.fixer_app180619;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerApiResponse {

    private Date date;
    private CurrencyRateResponse rates;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CurrencyRateResponse getRates() {
        return rates;
    }

    public void setRates(CurrencyRateResponse rates) {
        this.rates = rates;
    }
}
