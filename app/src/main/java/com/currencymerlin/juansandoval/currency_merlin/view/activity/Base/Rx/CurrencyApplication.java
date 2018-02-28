package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx;


import android.app.Application;

import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application.ApplicationComponent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application.ApplicationModule;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application.DaggerApplicationComponent;

public class CurrencyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
