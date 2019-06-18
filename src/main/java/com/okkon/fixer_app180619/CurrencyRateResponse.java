package com.okkon.fixer_app180619;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateResponse {

    @JsonProperty("USD")
    private double usdRate;

    @JsonProperty("RUB")
    private double rubRate;

    public double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }

    public double getRubRate() {
        return rubRate;
    }

    public void setRubRate(double rubRate) {
        this.rubRate = rubRate;
    }
}
