package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.view;


import android.content.Context;

import com.currencymerlin.juansandoval.currency_merlin.view.activity.BaseActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {
    private BaseActivity activity;

    public ViewModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public BaseActivity provideActivity() {
        return activity;
    }

    @Provides
    public Context provideContext() {
        return activity;
    }

    @Provides
    public FirebaseAnalytics provideFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(activity);
    }
}
