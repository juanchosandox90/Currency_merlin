package com.currencymerlin.juansandoval.currency_merlin.view.activity.view;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.EventStream;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.subscriber.SubscriberUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.util.CurrencyUtil;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.util.ViewUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.BaseActivity;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.CurrencyEvent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrencyInput extends LinearLayout {
    @Inject
    EventStream eventStream;

    @BindView(R.id.textViewBefore)
    TextView textViewBefore;
    @BindView(R.id.textViewAfter)
    TextView textViewAfter;
    @BindView(R.id.editText)
    EditText editText;

    private Currency currency;
    private boolean currencyBefore;
    private BaseActivity activity;

    public CurrencyInput(Context context) {
        super(context);
        init(context);
    }

    public CurrencyInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CurrencyInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ViewUtils.inflate(context, R.layout.view_amount_input, this, true, (activity, view) -> {
            this.activity = activity;
            activity.getComponent().inject(this);
            ButterKnife.bind(this);

            initialState();
            setupBehavior();
        });
    }

    private void initialState() {
        textViewBefore.setVisibility(GONE);
        textViewAfter.setVisibility(GONE);

        currencyBefore = CurrencyUtil.isCurrencySymbolBeforeValueInLocale();
    }

    private void setupBehavior() {
        streamCurrencyEvents();
        editTextListener();
    }

    @OnClick({R.id.textViewBefore, R.id.textViewAfter})
    public void onClickCurrency() {
        editText.requestFocus();
        ViewUtils.showKeyboard(activity);
    }

    private void streamCurrencyEvents() {
        eventStream.stream()
                .filter(e -> e instanceof CurrencyEvent && ((CurrencyEvent) e).type == CurrencyEvent.Type.CHANGE_CURRENCY_FROM)
                .map(e -> (Currency) ((CurrencyEvent) e).value)
                .subscribe(SubscriberUtils.onNext(this::onCurrency));
    }

    private void onCurrency(Currency currency) {
        this.currency = currency;
        textViewBefore.setText(currency.getCurrency().getSymbol() + " ");
        textViewAfter.setText(" " + currency.getCurrency().getSymbol());
    }

    private void editTextListener() {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            ViewUtils.hideKeyboard(activity);
            return true;
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    try {
                        double value = Double.parseDouble(s.toString().replace(",", "."));
                        editText.setHint("0");

                        if (currencyBefore) {
                            textViewBefore.setVisibility(VISIBLE);
                            textViewAfter.setVisibility(GONE);
                        } else {
                            textViewBefore.setVisibility(GONE);
                            textViewAfter.setVisibility(VISIBLE);
                        }

                        eventStream.post(new CurrencyEvent<>(value, CurrencyEvent.Type.CHANGE_AMOUNT));
                    } catch (Exception e) {
                        Snackbar.make(editText, R.string.Invalid_amount, Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    editText.setHint(R.string.Amount);

                    textViewBefore.setVisibility(GONE);
                    textViewAfter.setVisibility(GONE);
                }
            }
        });
    }
}
