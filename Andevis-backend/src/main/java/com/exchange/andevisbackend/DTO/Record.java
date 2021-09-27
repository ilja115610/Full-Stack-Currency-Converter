package com.exchange.andevisbackend.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Record {

    private String sourceCurrency;

    private String targetCurrency;

    private String calculatedRate;

    private String amount;

    @JsonFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    private LocalDateTime requestTime;

    public Record() {
    }

    public Record(String sourceCurrency, String targetCurrency, String calculatedRate, String amount, LocalDateTime requestTime) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.calculatedRate = calculatedRate;
        this.amount = amount;
        this.requestTime = requestTime;
    }


    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getCalculatedRate() {
        return calculatedRate;
    }

    public void setCalculatedRate(String calculatedRate) {
        this.calculatedRate = calculatedRate;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
