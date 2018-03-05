package com.github.nemoo2580.currencyconverter;

import com.github.nemoo2580.currencyconverter.exchange.ExchangeRates;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebAppApplication {

    @Autowired
    @Qualifier("nbp")
    private ExchangeRates exchangeRates;

    public static void main(String[] args) {
        SpringApplication.run(WebAppApplication.class, args);
    }

    @Bean
    InitializingBean initData() {
        return () -> {
            reloadRates();
        };
    }

    private void reloadRates() {
        exchangeRates.reloadRatesIfNeeded();
    }
}
