package com.example.service;

import com.example.model.ExchangeRates;
import com.example.model.GoldPrice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    public ExchangeRates getExchangeRates(int daysBack, String currencyCode) {
        String uri = String.format("http://api.nbp.pl/api/exchangerates/rates/c/%s/last/%d/?format=json", currencyCode, daysBack);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ExchangeRates> responseEntity = restTemplate.getForEntity(uri, ExchangeRates.class);
        return responseEntity.getBody();
    }

    public List<GoldPrice> getGoldPrices(int daysBack) {
        String uri = String.format("http://api.nbp.pl/api/cenyzlota/last/%d/?format=json", daysBack);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(uri, Object[].class);
        Object[] objects = responseEntity.getBody();

        return extractGoldPrices(objects);
    }

    private List<GoldPrice> extractGoldPrices(Object[] objects) {
        ObjectMapper mapper = new ObjectMapper();
        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, GoldPrice.class))
                .toList();
    }

    public double getAverageGoldPrice(List<GoldPrice> goldPrices) {
        double averageGoldPrice = goldPrices.stream()
                .mapToDouble(GoldPrice::getPrice)
                .average()
                .orElse(Double.NaN);

        return averageGoldPrice;
    }
}
