package com.exchange.andevisbackend.DTO;

public class RateResponse {

    private String sourceCurrencyName;

    private String targetCurrencyName;

    private String calculatedRate;

    public RateResponse() {
    }

    public RateResponse(String sourceCurrencyRate, String targetCurrencyRate, String calculatedRate) {
        this.sourceCurrencyName = sourceCurrencyRate;
        this.targetCurrencyName = targetCurrencyRate;
        this.calculatedRate = calculatedRate;
    }

    public String getSourceCurrencyName() {
        return sourceCurrencyName;
    }

    public void setSourceCurrencyName(String sourceCurrencyRate) {
        this.sourceCurrencyName = sourceCurrencyRate;
    }

    public String getTargetCurrencyName() {
        return targetCurrencyName;
    }

    public String getCalculatedRate() {
        return calculatedRate;
    }

    public void setCalculatedRate(String calculatedRate) {
        this.calculatedRate = calculatedRate;
    }

    public void setTargetCurrencyName(String targetCurrencyName) {
        this.targetCurrencyName = targetCurrencyName;
    }
}
