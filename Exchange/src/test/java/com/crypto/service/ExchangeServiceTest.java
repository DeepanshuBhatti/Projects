package com.crypto.service;

import com.crypto.model.ExchangeResponseData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeServiceTest {

    private final String KUCOIN = "KUCOIN";

    @InjectMocks
    private ExchangeService exchangeService;

    @Before
    public void init() {
        ReflectionTestUtils.setField(exchangeService, // inject into this object
                "exchangeToExchangeRatesMap", // assign to this field
                getDummyExchangeToExchangeRatesMap()); // object
    }

    @Test
    public void fetchExchangeRate() {
        BigDecimal value = exchangeService.fetchExchangeRate(KUCOIN, "BTC", "ETH");
        Assert.assertNotNull(value);
        Assert.assertEquals(BigDecimal.valueOf(34.5/7.8), value);

    }

    @Test
    public void getExchangeList() {
        List<String> ex = exchangeService.getExchangeList();
        Assert.assertNotNull(ex);
        Assert.assertEquals(17, ex.size());
    }

    @Test
    public void getCurrencyInfo() {
        ExchangeResponseData exchangeResponseData = exchangeService.getCurrencyInfo(KUCOIN, "BTC");
        Assert.assertNotNull(exchangeResponseData);
        Assert.assertEquals("BTC", exchangeResponseData.getSymbol());
    }

    private Map<String, Map<String, ExchangeResponseData>> getDummyExchangeToExchangeRatesMap() {
        Map<String, Map<String, ExchangeResponseData>> ex = new HashMap<>();

        ExchangeResponseData e = new ExchangeResponseData();
        e.setSymbol("BTC");
        e.setPriceUsd(34.5);
        ex.putIfAbsent(KUCOIN.toLowerCase(), new HashMap<>());
        ex.get(KUCOIN.toLowerCase()).putIfAbsent("BTC", e);


        e = new ExchangeResponseData();
        e.setSymbol("ETH");
        e.setPriceUsd(7.8);
        ex.putIfAbsent(KUCOIN.toLowerCase(), new HashMap<>());
        ex.get(KUCOIN.toLowerCase()).putIfAbsent("ETH", e);
        return ex;
    }
}