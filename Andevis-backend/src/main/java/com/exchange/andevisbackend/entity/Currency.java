package com.exchange.andevisbackend.entity;

import com.exchange.andevisbackend.common.CurrencyCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "currency")
public class Currency implements Comparable<Currency>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private CurrencyCode currencyCode;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "base_rate")
    private String baseRate;

    @Column(name = "rate_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rateDate;

    @Column(name = "rate_time")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime rateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private ConversionRecord conversionRecord;

    public Currency() {
    }

    public Currency(CurrencyCode currencyCode, String currencyName, String baseRate, LocalDate rateDate, LocalTime rateTime) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.baseRate = baseRate;
        this.rateDate = rateDate;
        this.rateTime = rateTime;
    }

    public ConversionRecord getConversionRecord() {
        return conversionRecord;
    }

    public void setConversionRecord(ConversionRecord conversionRecord) {
        this.conversionRecord = conversionRecord;
    }

    public LocalDate getRateDate() {
        return rateDate;
    }

    public void setRateDate(LocalDate rateDate) {
        this.rateDate = rateDate;
    }

    public LocalTime getRateTime() {
        return rateTime;
    }

    public void setRateTime(LocalTime rateTime) {
        this.rateTime = rateTime;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(String baseRate) {
        this.baseRate = baseRate;
    }


    @Override
    public String toString() {
        return "Currency{" +
                "currencyCode=" + currencyCode +
                ", baseRate='" + baseRate + '\'' +
                '}';
    }

    @Override
    public int compareTo(Currency o) {

        int i = this.currencyCode.getName().compareTo(o.currencyCode.getName());

        if(i != 0) return i;
        i = this.baseRate.compareTo(o.getBaseRate());
        if(i != 0) return i;

        return this.rateDate.compareTo(o.rateDate);
    }
}
