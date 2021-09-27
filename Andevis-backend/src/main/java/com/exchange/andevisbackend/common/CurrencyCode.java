package com.exchange.andevisbackend.common;

public enum CurrencyCode {


    USD("US Dollar"), JPY("Japanese yen"), BGN("Bulgarian lev"), CZK("Czech koruna"), DKK("Danish krone"),
    GBP("Pound sterling"), HUF("Hungarian foint"), PLN("Polish zloty"), RON("Romanian leu"), SEK("Swedish krone"),
    CHF("Swiss franc"), ISK("Icelandic krona"), NOK("Norwegian krone"), HRK("Croatian kuna"), RUB("Russian ruble"),
    TRY("Turkish lira"), AUD("Australian dollar"), BRL("Brazilian real"), CAD("Canadian dollar"), CNY("Chinese yuan"),
    HKD("Hong Kong dollar"), IDR("Indonesian rupiah"), ILS("Israeli new shekel"), INR("Indian rupee"), KRW("South Korean won"),
    MXN("Mexican peso"), MYR("Malaysian ringgit"), NZD("New Zealand dollar"), SGD("Singapore dollar"), ZAR("South African rand"),
    PHP("Philippine peso"), THB("Thai baht"), EUR("Euro");

    private final String name;

    CurrencyCode(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
