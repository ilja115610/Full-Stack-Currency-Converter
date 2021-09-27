package com.exchange.andevisbackend.DTO;

import com.exchange.andevisbackend.common.CurrencyCode;

public class RateRequest {

    private CurrencyCode sourceCurrency;

    private CurrencyCode targetCurrency;

    private String amount;

    private Long userId;

    public RateRequest(CurrencyCode sourceCurrency, CurrencyCode targetCurrency, String amount, Long userId) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.userId = userId;
    }

    public RateRequest() {
    }

    public CurrencyCode getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(CurrencyCode sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public CurrencyCode getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(CurrencyCode targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
