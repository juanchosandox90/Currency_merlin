package com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio.response.CurrencyExchangeRatesResponse;

import javax.inject.Inject;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.schedulers.Schedulers;

public class FixerIoResource {
    protected interface ApiInterface {
        @GET("latest")
        Observable<Response<CurrencyExchangeRatesResponse>> getCurrencyExchangeRates(@Query("base") String base);
    }

    public static class ApiAccess {
        private ApiInterface api;

        @Inject
        public ApiAccess(Retrofit rf) {
            api = rf.create(ApiInterface.class);
        }

        public Observable<Response<CurrencyExchangeRatesResponse>> getCurrencyExchangeRates() {
            return api.getCurrencyExchangeRates("USD")
                    .subscribeOn(Schedulers.io());
        }
    }
}