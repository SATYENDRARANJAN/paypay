package com.example.paypay.currencyDTO;

public  class CurrencyExchangeQuotes {
    public String countryPair ;
    public double rate ;

    public CurrencyExchangeQuotes(String countryPair, double rate) {
        this.countryPair = countryPair;
        this.rate = rate;
    }
}
