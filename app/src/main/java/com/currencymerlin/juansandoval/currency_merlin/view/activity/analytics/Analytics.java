package com.currencymerlin.juansandoval.currency_merlin.view.activity.analytics;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;

import com.currencymerlin.juansandoval.currency_merlin.view.activity.CurrencyEvent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

public class Analytics {
    private FirebaseAnalytics analytics;

    @SuppressLint("HardwareIds")
    @Inject
    public Analytics(Context context, FirebaseAnalytics analytics) {
        this.analytics = analytics;
        analytics.setUserId(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
    }

    public void newFavourite(String from, String to) {
        Bundle bundle = new Bundle();
        bundle.putString("from_currency", from);
        bundle.putString("to_currency", to);

        analytics.logEvent("new_favourite", bundle);
    }

    public void alreadyChosenFavourite(String from, String to) {
        Bundle bundle = new Bundle();
        bundle.putString("from_currency", from);
        bundle.putString("to_currency", to);

        analytics.logEvent("already_chosen_favourite", bundle);
    }

    public void changeAmount(double value) {
        Bundle bundle = new Bundle();
        bundle.putDouble("amount", value);

        analytics.logEvent("change_amount", bundle);
    }

    public void changeCurrency(CurrencyEvent.Type type, Currency currency) {
        Bundle bundle = new Bundle();

        if (type == CurrencyEvent.Type.CHANGE_CURRENCY_FROM) {
            bundle.putString("type", "from");
        } else {
            bundle.putString("type", "to");
        }

        bundle.putString("currency_id", currency.getId());

        analytics.logEvent("change_currency", bundle);
    }

    public void openDebug() {
        analytics.logEvent("open_debug", new Bundle());
    }
}
