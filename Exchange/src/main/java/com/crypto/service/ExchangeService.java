package com.crypto.service;

import com.crypto.model.ExchangeResponseData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.asynchttpclient.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ExchangeService {
    private Map<String, Map<String, ExchangeResponseData>> exchangeToExchangeRatesMap;
    private Map<String, BoundRequestBuilder> exchangeToBoundRequestBuilderMap;
    private List<String> exchangeList;
    private static final String EXCHANGES_URL = "https://dev-api.shrimpy.io/v1/exchanges/";
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public ExchangeService() {
        StopWatch sw = new StopWatch();
        sw.start();
        DefaultAsyncHttpClientConfig.Builder clientBuilder = Dsl.config();
        AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient(clientBuilder);
        BoundRequestBuilder builder = asyncHttpClient.prepareGet(EXCHANGES_URL);
        populateExchangesList(builder);
        populateBoundRequestMap(asyncHttpClient);
        sw.stop();
        LOGGER.log(Level.INFO, "ExchangeService Constructor took {0} ms", sw.getTotalTimeMillis());
    }

    @PostConstruct
    private void init() {
        final long timeInterval = 50000;
        Runnable runnable = () -> {
            while (true) {
                refreshExchangeRates();
                try {
                    Thread.sleep(timeInterval);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, e.getMessage());
                    break;
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /**
     * fetchExchangeRate REST API gets the exchange rate value from a currency to a currency for the given exchange
     *
     * @param exchangeName: Name of the exchange from where the rates have been picked
     * @param fromCurrency: currency to convert from
     * @param toCurrency:   currency to convert to
     * @return value of exchange rate in BigDecimal form
     */
    public BigDecimal fetchExchangeRate(String exchangeName, String fromCurrency, String toCurrency) {
        StopWatch sw = new StopWatch();
        sw.start();

        if (!exchangeToExchangeRatesMap.containsKey(exchangeName.toLowerCase())) {
            return BigDecimal.ZERO;
        }
        Double fromCurrRate =
                exchangeToExchangeRatesMap.get(exchangeName.toLowerCase()).get(fromCurrency).getPriceUsd();
        Double toCurrRate = exchangeToExchangeRatesMap.get(exchangeName.toLowerCase()).get(toCurrency).getPriceUsd();

        sw.stop();
        LOGGER.log(Level.INFO, "fetchExchangeRate took {0} ms", sw.getTotalTimeMillis());
        return BigDecimal.valueOf(fromCurrRate / toCurrRate);
    }

    /**
     * getExchangeList is REST API which fetches names of exchanges
     *
     * @return exchange names in list form
     */
    public List<String> getExchangeList() {
        return exchangeList;
    }

    /**
     * getCurrencyInfo REST API returns currency info from the exchange
     * @param exchangeName: Name of the exchange to fetch data from
     * @param currency: Currency abbrev for with info is required
     * @return ExchangeResponseData
     */
    public ExchangeResponseData getCurrencyInfo(String exchangeName, String currency) {
        StopWatch sw = new StopWatch();
        sw.start();
        ExchangeResponseData exchangeResponseData = new ExchangeResponseData();
        try {
            if (!exchangeToExchangeRatesMap.containsKey(exchangeName.toLowerCase())) {
                throw new IllegalArgumentException("Exchange " + exchangeName + " not found!");
            }
            if (!exchangeToExchangeRatesMap.get(exchangeName.toLowerCase()).containsKey(currency.toUpperCase())) {
                throw new IllegalArgumentException("No " + currency.toUpperCase() + " currency found in Exchange " + exchangeName);
            }
            exchangeResponseData =
                    exchangeToExchangeRatesMap.get(exchangeName.toLowerCase()).get(currency.toUpperCase());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        sw.stop();
        LOGGER.log(Level.INFO, "getCurrencyInfo took {0} ms", sw.getTotalTimeMillis());
        return exchangeResponseData;
    }

    private void populateExchangesList(BoundRequestBuilder builder) {
        Future<Response> response = builder.execute();
        Gson gson = new Gson();
        exchangeList = new ArrayList<>();
        try {
            exchangeList = gson.fromJson(
                    response.get().getResponseBody(),
                    new TypeToken<List<String>>() {
                    }.getType()
            );
            LOGGER.log(Level.INFO, "Got {0} data from Exchanges API", exchangeList.size());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            e.getStackTrace();
        }
    }

    private void populateBoundRequestMap(AsyncHttpClient asyncHttpClient) {
        exchangeToBoundRequestBuilderMap = new HashMap<>();
        for (String exchange : exchangeList) {
            String url = EXCHANGES_URL + exchange + "/ticker";
            LOGGER.log(Level.INFO, url);
            BoundRequestBuilder tempBuilder = asyncHttpClient.prepareGet(url);
            exchangeToBoundRequestBuilderMap.putIfAbsent(exchange, tempBuilder);
        }
    }

    private void refreshExchangeRates() {
        StopWatch sw = new StopWatch();
        sw.start();
        exchangeToExchangeRatesMap = new HashMap<>();

        for (Map.Entry<String, BoundRequestBuilder> exchangeBuilder : exchangeToBoundRequestBuilderMap.entrySet()) {
            Future<Response> response = exchangeBuilder.getValue().execute();
            List<ExchangeResponseData> exchangeResponseDataList = new ArrayList<>();
            Gson gson = new Gson();
            try {
                exchangeResponseDataList = gson.fromJson(
                        response.get().getResponseBody(),
                        new TypeToken<List<ExchangeResponseData>>() {
                        }.getType()
                );
                LOGGER.log(Level.INFO, "For key " + exchangeBuilder.getKey() + ", Got " + exchangeResponseDataList.size() + " data from API");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
                e.getStackTrace();
            }
            exchangeToExchangeRatesMap.putIfAbsent(exchangeBuilder.getKey(), new HashMap<>());
            for (ExchangeResponseData exchangeResponseData : exchangeResponseDataList) {
                exchangeToExchangeRatesMap.get(exchangeBuilder.getKey()).putIfAbsent(exchangeResponseData.getSymbol(),
                        exchangeResponseData);
            }
        }
        sw.stop();
        LOGGER.log(Level.INFO, "refreshExchangeRates took {0} ms", sw.getTotalTimeMillis());
    }

}
