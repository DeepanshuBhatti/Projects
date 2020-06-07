package com.crypto;

import com.crypto.model.ExchangeResponseData;
import com.crypto.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(path = "/getExchangeRate/{exchangeName}")
    public BigDecimal getExchangeRate(
            @PathVariable("exchangeName") String exchangeName,
            @RequestParam(value = "fromCurrency") String fromCurrency,
            @RequestParam(value = "toCurrency") String toCurrency
    ) {
        return exchangeService.fetchExchangeRate(exchangeName, fromCurrency, toCurrency);
    }

    @GetMapping(path = "/getExchangeList")
    public List<String> getExchangeList() {
        return exchangeService.getExchangeList();
    }

    @GetMapping(path = "/getCurrencyInfo/{exchangeName}")
    public ExchangeResponseData getCurrencyInfo(
            @PathVariable("exchangeName") String exchangeName,
            @RequestParam(value = "currency") String currency
    ) {
        return exchangeService.getCurrencyInfo(exchangeName, currency);
    }
}
