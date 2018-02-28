package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.api.fixerio.FixerIoResource;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.currency.MockStorage;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.preferences.PersistentStorage;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.source.CurrencySource;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.source.PreferencesSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SourceModule {
    @Provides
    @Singleton
    public CurrencySource provideCurrencySource(FixerIoResource.ApiAccess apiAccess, MockStorage currencyStorage) {
        return new CurrencySource(apiAccess, currencyStorage);
    }

    @Provides
    @Singleton
    public PreferencesSource providePreferencesSource(PersistentStorage storage) {
        return new PreferencesSource(storage);
    }
}
