package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class that represents gold price in the given day
 */
@Data
public class GoldPrice {

    @JsonProperty("data")
    private String date;

    @JsonProperty("cena")
    private double price;
}
