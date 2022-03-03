package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("code")
    private String code;

    @JsonProperty("rates")
    private List<Rate> rates;

}
