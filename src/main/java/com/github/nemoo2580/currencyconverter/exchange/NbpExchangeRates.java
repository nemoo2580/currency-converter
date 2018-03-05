package com.github.nemoo2580.currencyconverter.exchange;

import com.github.nemoo2580.currencyconverter.dto.RateDto;
import com.github.nemoo2580.currencyconverter.dto.RatesTableDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Qualifier("nbp")
public class NbpExchangeRates extends AbstractExchangeRates {

    @Override
    public void loadExchangeRates(List<RateDto> newRates) {
        loadExchangeRatesTable("A", newRates);
        loadExchangeRatesTable("B", newRates);
    }

    private void loadExchangeRatesTable(String tableName, List<RateDto> newRates) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<RatesTableDto>> rateResponse;
        try {
            rateResponse = restTemplate.exchange("http://api.nbp.pl/api/exchangerates/tables/" + tableName,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<RatesTableDto>>() {});
        } catch (RestClientException ex) {
            throw new IllegalStateException("NBP convert rates api unavailable");
        }

        List<RatesTableDto> ratesTableList = rateResponse.getBody();
        if (!CollectionUtils.isEmpty(ratesTableList)) {
            newRates.addAll(ratesTableList.get(0).getRates());
        }
    }
}
