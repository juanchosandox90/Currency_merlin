package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio.response.CurrencyExchangeRatesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    protected static OkHttpClient.Builder okHttpBuilder(Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        return builder;
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl("http://api.fixer.io")
                .addConverterFactory(new Converter.Factory() {
                    @Override
                    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        if (type == CurrencyExchangeRatesResponse.class) {
                            return value -> CurrencyExchangeRatesResponse.parse(value.byteStream());
                        }

                        return null; //leave conversion to GSON
                    }
                })
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpBuilder().build())
                .build();
    }
}
