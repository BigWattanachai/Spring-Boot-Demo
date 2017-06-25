package com.ascend.springbootdemo.models;

import lombok.Data;

import java.util.Map;

/**
 * Created by BiG on 6/25/2017 AD.
 */
@Data
public class CurrencyRates {
    private String base;
    private String date;
    private Map<String, Double> rates;
}
