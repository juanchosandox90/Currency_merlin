package com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency.service;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency.CurrencyExchange;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyExchangeService {
    @GET("latest")
    Call<CurrencyExchange> loadCurrencyExchange();
}
