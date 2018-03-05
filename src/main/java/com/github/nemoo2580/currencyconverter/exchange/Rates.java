package com.github.nemoo2580.currencyconverter.exchange;

import com.github.nemoo2580.currencyconverter.dto.RateDto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScope
@Component
public class Rates implements Serializable {

    private LocalDateTime lastUpdate;
    private List<RateDto> rates = new ArrayList<>();

    public Rates() {
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<RateDto> getRates() {
        return rates;
    }

    public void setRates(List<RateDto> rates) {
        this.rates = rates;
    }
}
