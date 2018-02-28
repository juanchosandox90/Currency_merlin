package com.currencymerlin.juansandoval.currency_merlin.view.activity.source;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio.FixerIoResource;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio.model.CurrencyRateRespModel;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio.response.CurrencyExchangeRatesResponse;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.currency.CurrencyStorage;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import rx.Observable;

public class CurrencySource {
    private FixerIoResource.ApiAccess api;
    private CurrencyStorage cache;

    public CurrencySource(FixerIoResource.ApiAccess api, CurrencyStorage cache) {
        this.cache = cache;
        this.api = api;
    }

    public Observable<List<Currency>> getCurrencies() {
        return cache.getAll()
                .switchIfEmpty(fromApi());
    }

    public Observable<Currency> getCurrency(String id) {
        return cache.get(id)
                .switchIfEmpty(fromApi().flatMap(Observable::from).filter(e -> e.getId().equals(id)).first());
    }

    protected Observable<List<Currency>> fromApi() {
        return api.getCurrencyExchangeRates()
                .map(Response::body)
                .map(this::transform)
                .map(this::addBase)
                .doOnNext(cache::put);
    }

    private List<Currency> addBase(List<Currency> currencies) {
        currencies.add(new Currency(Currency.BASE_ID, 1.0, java.util.Currency.getInstance(Currency.BASE_ID)));

        return currencies;
    }

    private List<Currency> transform(CurrencyExchangeRatesResponse resp) {
        List<Currency> currencies = new ArrayList<>();

        for (CurrencyRateRespModel model : resp.getRates()) {
            currencies.add(new Currency(model.id, model.value, java.util.Currency.getInstance(model.id)));
        }

        return currencies;
    }
}
