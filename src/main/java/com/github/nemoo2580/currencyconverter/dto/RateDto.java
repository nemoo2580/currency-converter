package com.github.nemoo2580.currencyconverter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDto implements Serializable {

    private String currency;
    private String code;
    private Double mid;

    public RateDto() {
    }

    public RateDto(String currency, String code, Double mid) {
        this.currency = currency;
        this.code = code;
        this.mid = mid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "RateDto{" +
                "currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", mid='" + mid + '\'' +
                '}';
    }
}
