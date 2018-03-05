package com.github.nemoo2580.currencyconverter.exchange;

public interface ExchangeRates {

    /**
     * Converts base currency to target currency
     * @param amount amount of base currency
     * @param baseCurrencyCode base currency code ex. "PLN"
     * @param targetCurrencyCode target currency code ex. "USD"
     * @return amount in target currency
     */
    Double convert(Double amount, String baseCurrencyCode, String targetCurrencyCode);

    /**
     * Reloads currency rates once a day
     */
    void reloadRatesIfNeeded();
}
