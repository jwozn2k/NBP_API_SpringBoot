package com.example.controller;

import com.example.model.ExchangeRates;
import com.example.model.GoldPrice;
import com.example.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/exchange-rates/{currencyCode}")
    public String getCurrencyExchangeRate(@PathVariable String currencyCode, Model model) {
        int daysBack = 5;
        ExchangeRates exchangeRates = currencyService.getExchangeRates(daysBack, currencyCode);

        model.addAttribute("currencyCode", currencyCode);
        model.addAttribute("currency", exchangeRates.getCurrency());
        model.addAttribute("code", exchangeRates.getCode());
        model.addAttribute("rates", exchangeRates.getRates());

        return "exchange-rates";
    }

    @GetMapping("/gold-price/average")
    public String getAverageGoldPrice(Model model) {
        int daysBack = 14;
        List<GoldPrice> goldPrices = currencyService.getGoldPrices(daysBack);
        double averageGoldPrice = currencyService.getAverageGoldPrice(goldPrices);

        model.addAttribute("averageGoldPrice", averageGoldPrice);
        model.addAttribute("goldPrices", goldPrices);

        return "gold-prices";
    }
}
