package com.github.nemoo2580.currencyconverter.controller;

import com.github.nemoo2580.currencyconverter.service.ConvertService;
import com.github.nemoo2580.currencyconverter.dto.ConvertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/convert")
public class ConvertController {

    @Autowired
    private ConvertService convertService;

    @GetMapping()
    public Double exchangeGet(
            @RequestParam(name = "amount") Double amount,
            @RequestParam(name = "baseCurrency") String baseCurrency,
            @RequestParam(name = "targetCurrency") String targetCurrency) {
        return convertService.exchange(amount, baseCurrency, targetCurrency);
    }

    @PostMapping()
    public Double exchangePost(@RequestBody ConvertDto convertDto) {
        return convertService.exchange(convertDto.getAmount(), convertDto.getBaseCurrency(), convertDto.getTargetCurrency());
    }

    @ExceptionHandler(NumberFormatException.class)
    public String numberFormatExceptionHandler() {
        return "Given amount must be a number";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String illegalStateExceptionHandler(IllegalStateException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RestClientException.class)
    public String restClientExceptionHandler(IllegalStateException ex) {
        return ex.getMessage();
    }
}
