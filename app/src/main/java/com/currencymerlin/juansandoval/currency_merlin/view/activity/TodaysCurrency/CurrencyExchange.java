package com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency;

import com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency.model.CurrencyToday;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchange {

    private String base;
    private String date;

    private RatesEntity rates;

    public List<CurrencyToday> getCurrencyList() {
        List<CurrencyToday> currencyList = new ArrayList<>();
        currencyList.add(new CurrencyToday("AUD", rates.getAUD()));
        currencyList.add(new CurrencyToday("BGN", rates.getBGN()));
        currencyList.add(new CurrencyToday("BRL", rates.getBRL()));
        currencyList.add(new CurrencyToday("CAD", rates.getCAD()));
        currencyList.add(new CurrencyToday("CHF", rates.getCHF()));

        currencyList.add(new CurrencyToday("CNY", rates.getCNY()));
        currencyList.add(new CurrencyToday("CZK", rates.getCZK()));
        currencyList.add(new CurrencyToday("DKK", rates.getDKK()));
        currencyList.add(new CurrencyToday("GBP", rates.getGBP()));
        currencyList.add(new CurrencyToday("HKD", rates.getHKD()));

        currencyList.add(new CurrencyToday("HRK", rates.getHRK()));
        currencyList.add(new CurrencyToday("HUF", rates.getHUF()));
        currencyList.add(new CurrencyToday("IDR", rates.getIDR()));
        currencyList.add(new CurrencyToday("ILS", rates.getILS()));
        currencyList.add(new CurrencyToday("INR", rates.getINR()));

        currencyList.add(new CurrencyToday("JPY", rates.getJPY()));
        currencyList.add(new CurrencyToday("KRW", rates.getKRW()));
        currencyList.add(new CurrencyToday("MXN", rates.getMXN()));
        currencyList.add(new CurrencyToday("MYR", rates.getMYR()));
        currencyList.add(new CurrencyToday("NOK", rates.getNOK()));

        currencyList.add(new CurrencyToday("NZD", rates.getNZD()));
        currencyList.add(new CurrencyToday("PHP", rates.getPHP()));
        currencyList.add(new CurrencyToday("PLN", rates.getPLN()));
        currencyList.add(new CurrencyToday("RON", rates.getRON()));
        currencyList.add(new CurrencyToday("RUB", rates.getRUB()));

        currencyList.add(new CurrencyToday("SEK", rates.getSEK()));
        currencyList.add(new CurrencyToday("SGD", rates.getSGD()));
        currencyList.add(new CurrencyToday("THB", rates.getTHB()));
        currencyList.add(new CurrencyToday("TRY", rates.getTRY()));
        currencyList.add(new CurrencyToday("USD", rates.getUSD()));

        currencyList.add(new CurrencyToday("ZAR", rates.getZAR()));

        return currencyList;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RatesEntity getRates() {
        return rates;
    }

    public void setRates(RatesEntity rates) {
        this.rates = rates;
    }

    public static class RatesEntity {
        private double AUD;
        private double BGN;
        private double BRL;
        private double CAD;
        private double CHF;
        private double CNY;
        private double CZK;
        private double DKK;
        private double GBP;
        private double HKD;
        private double HRK;
        private double HUF;
        private double IDR;
        private double ILS;
        private double INR;
        private double JPY;
        private double KRW;
        private double MXN;
        private double MYR;
        private double NOK;
        private double NZD;
        private double PHP;
        private double PLN;
        private double RON;
        private double RUB;
        private double SEK;
        private double SGD;
        private double THB;
        private double TRY;
        private double USD;
        private double ZAR;
        private double EUR;

        public double getAUD() {
            return AUD;
        }

        public void setAUD(double AUD) {
            this.AUD = AUD;
        }

        public double getBGN() {
            return BGN;
        }

        public void setBGN(double BGN) {
            this.BGN = BGN;
        }

        public double getBRL() {
            return BRL;
        }

        public void setBRL(double BRL) {
            this.BRL = BRL;
        }

        public double getCAD() {
            return CAD;
        }

        public void setCAD(double CAD) {
            this.CAD = CAD;
        }

        public double getCHF() {
            return CHF;
        }

        public void setCHF(double CHF) {
            this.CHF = CHF;
        }

        public double getCNY() {
            return CNY;
        }

        public void setCNY(double CNY) {
            this.CNY = CNY;
        }

        public double getCZK() {
            return CZK;
        }

        public void setCZK(double CZK) {
            this.CZK = CZK;
        }

        public double getDKK() {
            return DKK;
        }

        public void setDKK(double DKK) {
            this.DKK = DKK;
        }

        public double getGBP() {
            return GBP;
        }

        public void setGBP(double GBP) {
            this.GBP = GBP;
        }

        public double getHKD() {
            return HKD;
        }

        public void setHKD(double HKD) {
            this.HKD = HKD;
        }

        public double getHRK() {
            return HRK;
        }

        public void setHRK(double HRK) {
            this.HRK = HRK;
        }

        public double getHUF() {
            return HUF;
        }

        public void setHUF(double HUF) {
            this.HUF = HUF;
        }

        public double getIDR() {
            return IDR;
        }

        public void setIDR(double IDR) {
            this.IDR = IDR;
        }

        public double getILS() {
            return ILS;
        }

        public void setILS(double ILS) {
            this.ILS = ILS;
        }

        public double getINR() {
            return INR;
        }

        public void setINR(double INR) {
            this.INR = INR;
        }

        public double getJPY() {
            return JPY;
        }

        public void setJPY(double JPY) {
            this.JPY = JPY;
        }

        public double getKRW() {
            return KRW;
        }

        public void setKRW(double KRW) {
            this.KRW = KRW;
        }

        public double getMXN() {
            return MXN;
        }

        public void setMXN(double MXN) {
            this.MXN = MXN;
        }

        public double getMYR() {
            return MYR;
        }

        public void setMYR(double MYR) {
            this.MYR = MYR;
        }

        public double getNOK() {
            return NOK;
        }

        public void setNOK(double NOK) {
            this.NOK = NOK;
        }

        public double getNZD() {
            return NZD;
        }

        public void setNZD(double NZD) {
            this.NZD = NZD;
        }

        public double getPHP() {
            return PHP;
        }

        public void setPHP(double PHP) {
            this.PHP = PHP;
        }

        public double getPLN() {
            return PLN;
        }

        public void setPLN(double PLN) {
            this.PLN = PLN;
        }

        public double getRON() {
            return RON;
        }

        public void setRON(double RON) {
            this.RON = RON;
        }

        public double getRUB() {
            return RUB;
        }

        public void setRUB(double RUB) {
            this.RUB = RUB;
        }

        public double getSEK() {
            return SEK;
        }

        public void setSEK(double SEK) {
            this.SEK = SEK;
        }

        public double getSGD() {
            return SGD;
        }

        public void setSGD(double SGD) {
            this.SGD = SGD;
        }

        public double getTHB() {
            return THB;
        }

        public void setTHB(double THB) {
            this.THB = THB;
        }

        public double getTRY() {
            return TRY;
        }

        public void setTRY(double TRY) {
            this.TRY = TRY;
        }

        public double getUSD() {
            return USD;
        }

        public void setUSD(double USD) {
            this.USD = USD;
        }

        public double getZAR() {
            return ZAR;
        }

        public void setZAR(double ZAR) {
            this.ZAR = ZAR;
        }

        public double getEUR() {
            return EUR;
        }

        public void setEUR(double EUR) {
            this.EUR = EUR;
        }
    }
}

