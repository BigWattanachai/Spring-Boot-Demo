package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.models.CurrencyRates;
import com.ascend.springbootdemo.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;

import java.util.Set;

/**
 * Created by BiG on 6/25/2017 AD.
 */
@RestController
@RequestMapping("/api/v1/currencyconverter")
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(value = "/rates")
    public Observable<CurrencyRates> getCurrencyRates(
            @RequestParam("symbol") Set<String> currencyRates) {
        return currencyService.getCurrencyRates(currencyRates);
    }
}
