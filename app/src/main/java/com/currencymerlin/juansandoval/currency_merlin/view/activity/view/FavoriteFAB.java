package com.currencymerlin.juansandoval.currency_merlin.view.activity.view;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.EventStream;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.subscriber.SubscriberUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.util.ViewUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.CurrencyEvent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.analytics.Analytics;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.preferences.PreferencesHelper;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.source.PreferencesSource;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoriteFAB extends FloatingActionButton {
    @Inject
    EventStream eventStream;
    @Inject
    PreferencesSource preferencesSource;
    @Inject
    Analytics analytics;

    private Currency from;
    private Currency to;

    public FavoriteFAB(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FavoriteFAB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (!isInEditMode()) {
            ViewUtils.getBaseActivity(context).getComponent().inject(this);
            ButterKnife.bind(this);

            setupBehavior();
        }
    }

    @OnClick
    public void onClick() {
        String fromId = preferencesSource.<String>get(PreferencesHelper.CURRENCY_FROM);
        String toId = preferencesSource.<String>get(PreferencesHelper.CURRENCY_TO);

        if (fromId == null || toId == null || (!fromId.equals(from.getId()) || !toId.equals(to.getId()))) {
            preferencesSource.put(PreferencesHelper.CURRENCY_FROM, from.getId());
            preferencesSource.put(PreferencesHelper.CURRENCY_TO, to.getId());

            onFavoriteConversion();

            Snackbar.make(this, getResources().getString(R.string.ARG1_to_ARG2_set_as_your_standard_conversion_ARG3, from.getId(), to.getId(), "\uD83D\uDCB0"), Snackbar.LENGTH_LONG).show();

            analytics.newFavourite(from.getId(), to.getId());
        } else {
            Snackbar.make(this, getContext().getString(R.string.This_is_already_your_standard_conversion_ARG1, "\uD83D\uDCB0"), Snackbar.LENGTH_LONG).show();
            analytics.alreadyChosenFavourite(fromId, toId);
        }
    }

    private void setupBehavior() {
        eventStream.stream()
                .filter(e -> e instanceof CurrencyEvent)
                .cast(CurrencyEvent.class)
                .subscribe(SubscriberUtils.onNext(this::onCurrencyEvent));
    }

    private void onCurrencyEvent(CurrencyEvent event) {
        if (event.type == CurrencyEvent.Type.CHANGE_CURRENCY_FROM) {
            from = (Currency) event.value;
        } else if (event.type == CurrencyEvent.Type.CHANGE_CURRENCY_TO) {
            to = (Currency) event.value;
        }

        if (from != null && to != null) {
            String fromId = preferencesSource.<String>get(PreferencesHelper.CURRENCY_FROM);
            String toId = preferencesSource.<String>get(PreferencesHelper.CURRENCY_TO);

            if (from.getId().equals(fromId) && to.getId().equals(toId)) {
                onFavoriteConversion();
            } else {
                onNotFavoriteConversion();
            }
        }
    }

    private void onFavoriteConversion() {
        setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.colorAccent));
    }

    private void onNotFavoriteConversion() {
        setImageTintList(ContextCompat.getColorStateList(getContext(), R.color.fabIconNormal));
    }
}
