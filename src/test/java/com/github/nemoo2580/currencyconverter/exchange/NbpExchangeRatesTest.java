package com.github.nemoo2580.currencyconverter.exchange;

import com.github.nemoo2580.currencyconverter.dto.RateDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NbpExchangeRatesTest {

    @Autowired
    @Qualifier("nbp")
    @InjectMocks
    private ExchangeRates exchangeRates;

    @Mock
    private Rates rates;

    @Before
    public void before() {
        List<RateDto> ratesList = new ArrayList<>();
        ratesList.add(new RateDto("Złoty polski", "PLN", 1.0));
        ratesList.add(new RateDto("Euro", "EUR", 4.3));
        ratesList.add(new RateDto("Dolar", "USD", 3.6));
        Mockito.when(rates.getLastUpdate()).thenReturn(LocalDateTime.now());
        Mockito.when(rates.getRates()).thenReturn(ratesList);
    }

    @Test
    public void exchangeUsdToPlnTest() {
        Double amount = 100.0;
        String baseCurrency = "USD";
        String targetCurrency = "PLN";

        Double result = exchangeRates.convert(amount, baseCurrency, targetCurrency);

        Assert.assertEquals(360.0, result, 0.001);

    }

    @Test
    public void exchangePlnToPlnTest() {
        Double amount = 100.0;
        String baseCurrency = "PLN";
        String targetCurrency = "PLN";

        Double result = exchangeRates.convert(amount, baseCurrency, targetCurrency);

        Assert.assertEquals(100.0, result, 0.001);

    }
}
