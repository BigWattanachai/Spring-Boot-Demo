package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.models.CurrencyRates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import rx.Observable;

import java.util.Set;

/**
 * Created by BiG on 6/25/2017 AD.
 */
@Service
public class CurrencyService {
    @Value("${services.currency.uri}")
    private String CURRENCY_SERVICE_API;

    private static final String SYMBOLS = "symbols";

    private static final Logger log = LoggerFactory.getLogger(CurrencyService.class);

    private RestTemplate restTemplate = new RestTemplate();

    public Observable<CurrencyRates> getCurrencyRates(Set<String> currencyRates) {
        return getCurrencyRatesObservable(currencyRates);
    }

    private Observable<CurrencyRates> getCurrencyRatesObservable(Set<String> currencies) {
        return Observable.create(getCurrencyRatesOnSubscribe(currencies)).doOnNext(c -> log.debug("Successfully."))
                .doOnError(e -> log.error("An ERROR occurred while retrieving the currency rates.", e));
    }

    private Observable.OnSubscribe<CurrencyRates> getCurrencyRatesOnSubscribe(Set<String> currencies) {
        return sub -> {
            CurrencyRates currencyRatesDTO = restTemplate.getForEntity(UriComponentsBuilder
                    .fromUriString(CURRENCY_SERVICE_API)
                    .queryParam(SYMBOLS, currencies.toArray()).toUriString(), CurrencyRates.class).getBody();
            sub.onNext(currencyRatesDTO);
            sub.onCompleted();
        };
    }
}
