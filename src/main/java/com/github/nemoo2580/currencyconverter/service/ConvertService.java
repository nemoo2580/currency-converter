package com.github.nemoo2580.currencyconverter.service;

import com.github.nemoo2580.currencyconverter.exchange.ExchangeRates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class ConvertService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    @Qualifier("nbp")
    private ExchangeRates exchangeRates;

    /**
     * Converts base currency to target currency
     * @param amount amount of base currency
     * @param baseCurrencyCode base currency code ex. "PLN"
     * @param targetCurrencyCode target currency code ex. "USD"
     * @return amount in target currency
     */
    public Double exchange(Double amount, String baseCurrencyCode, String targetCurrencyCode) {
        log.info("Exchange currency (amount: {}, baseCurrencyCode: {}, targetCurrencyCode: {}", amount,
                baseCurrencyCode, targetCurrencyCode);

        return exchangeRates.convert(amount, baseCurrencyCode, targetCurrencyCode);
    }
}
