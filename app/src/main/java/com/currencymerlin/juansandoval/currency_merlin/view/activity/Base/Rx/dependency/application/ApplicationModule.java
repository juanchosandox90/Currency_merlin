package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.CurrencyApplication;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.EventStream;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private CurrencyApplication app;

    public ApplicationModule(CurrencyApplication app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public CurrencyApplication provideApplication() {
        return app;
    }

    @Singleton
    @Provides
    public EventStream provideEventStream() {
        return new EventStream();
    }

}
