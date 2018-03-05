package com.github.nemoo2580.currencyconverter.exchange;

import com.github.nemoo2580.currencyconverter.dto.RateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExchangeRates implements ExchangeRates {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private Rates rates;

    @Override
    public Double convert(Double amount, String baseCurrencyCode, String targetCurrencyCode) {
        reloadRatesIfNeeded();

        Double baseCurrencyRate = getCurrencyRate(baseCurrencyCode);
        Double targetCurrencyRate = getCurrencyRate(targetCurrencyCode);

        Double localAmount = amount * baseCurrencyRate;
        return localAmount / targetCurrencyRate;
    }

    @Override
    public void reloadRatesIfNeeded() {
        if (rates.getLastUpdate() != null && LocalDateTime.now().isBefore(rates.getLastUpdate().plusDays(1))) {
            return;
        }
        log.info("Reload currency rates");

        List<RateDto> newRates = new ArrayList<>();
        loadExchangeRates(newRates);
        newRates.add(getPlnRate());
        rates.setRates(newRates);
        rates.setLastUpdate(LocalDateTime.now());
    }

    public abstract void loadExchangeRates(List<RateDto> newRates);

    private Double getCurrencyRate(String currencyCode) {
        if (StringUtils.isEmpty(currencyCode)) {
            throw new IllegalStateException(String.format("Currency code %s cannot be empty", currencyCode));
        }

        RateDto rate = rates.getRates().stream().filter(r -> r.getCode().contentEquals(currencyCode))
                .findFirst().orElse(null);
        if (rate == null) {
            throw new IllegalStateException(String.format("Unknown currency code %s", currencyCode));
        }

        return rate.getMid();
    }

    private RateDto getPlnRate() {
        RateDto rate = new RateDto();
        rate.setCurrency("Złoty polski");
        rate.setCode("PLN");
        rate.setMid(1.0);
        return rate;
    }
}
