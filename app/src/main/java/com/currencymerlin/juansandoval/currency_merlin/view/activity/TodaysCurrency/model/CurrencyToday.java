package com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency.model;


public class CurrencyToday {
    private String name;
    private double rate;

    public CurrencyToday(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
