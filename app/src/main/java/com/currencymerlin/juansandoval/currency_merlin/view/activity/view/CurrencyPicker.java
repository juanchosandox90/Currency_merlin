package com.currencymerlin.juansandoval.currency_merlin.view.activity.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.util.ViewUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.CurrencyEvent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.currencypicker.internal.CurrencyPickerRecycler;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrencyPicker extends FrameLayout {
    @BindView(R.id.currencyRecycler)
    CurrencyPickerRecycler currencyRecycler;

    public CurrencyPicker(Context context) {
        super(context);
        init(context);
    }

    public CurrencyPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CurrencyPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CurrencyPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        ViewUtils.inflate(context, R.layout.view_currency_picker, this, true, (activity, view) -> {
            ButterKnife.bind(this, view);
        });
    }

    public void applyItems(List<Currency> items, String initialCurrency) {
        currencyRecycler.applyItems(items, initialCurrency);
    }

    public void setEventType(CurrencyEvent.Type eventType) {
        currencyRecycler.setEventType(eventType);
    }
}
