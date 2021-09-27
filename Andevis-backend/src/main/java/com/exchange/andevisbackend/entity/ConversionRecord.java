package com.exchange.andevisbackend.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_record")
public class ConversionRecord implements Comparable<ConversionRecord>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "source_currency_id", referencedColumnName = "id")
    private Currency sourceCurrency;

    @OneToOne
    @JoinColumn(name = "target_currency_id", referencedColumnName = "id")
    private Currency targetCurrency;

    @Column(name = "amount")
    private String amount;

    @Column(name = "calculated_rate")
    private String calculatedRate;

    @Column(name = "conversion_date")
    private LocalDateTime conversionDate;

    @ManyToOne
    private User user;

    public ConversionRecord() {
    }

    public ConversionRecord(Currency sourceCurrency, Currency targetCurrency,String amount, String calculatedRate, LocalDateTime conversionDate, User user) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.calculatedRate = calculatedRate;
        this.conversionDate = conversionDate;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getCalculatedRate() {
        return calculatedRate;
    }

    public void setCalculatedRate(String calculatedRate) {
        this.calculatedRate = calculatedRate;
    }

    public LocalDateTime getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(LocalDateTime conversionDate) {
        this.conversionDate = conversionDate;
    }

    @Override
    public int compareTo(ConversionRecord o) {
        return this.conversionDate.compareTo(o.conversionDate);
    }
}
