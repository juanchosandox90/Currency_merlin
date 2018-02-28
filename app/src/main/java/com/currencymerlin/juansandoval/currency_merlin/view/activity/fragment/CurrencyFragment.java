package com.currencymerlin.juansandoval.currency_merlin.view.activity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.RxUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.Event;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.EventStream;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.util.ViewUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.CurrencyEvent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency.CurrencyTodayActivity;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.KeyboardEvent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.analytics.Analytics;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.source.CurrencySource;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.source.PreferencesSource;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.CurrencyInput;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.CurrencyPicker;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.CurrencyTextView;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.FavoriteFAB;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.subscriber.SubscriberUtils.onNext;

import static com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.preferences.PreferencesHelper.CURRENCY_FROM;
import static com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.preferences.PreferencesHelper.CURRENCY_TO;

public class CurrencyFragment extends BaseFragment {
    @Inject
    CurrencySource source;
    @Inject
    PreferencesSource preferencesSource;
    @Inject
    EventStream eventStream;
    @Inject
    Analytics analytics;

    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.currencyPickerFrom)
    CurrencyPicker currencyPickerFrom;
    @BindView(R.id.currencyPickerTo)
    CurrencyPicker currencyPickerTo;
    @BindView(R.id.currencyEditText)
    CurrencyInput currencyInput;
    @BindView(R.id.currencyTextView)
    CurrencyTextView currencyTextView;
    @BindView(R.id.favFAB)
    FavoriteFAB favFAB;

    private static int debugClicks = 0;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBaseActivity().getComponent().inject(this);
        setupViews();
        setupBehavior();
        initialState();
    }

    private void setupViews() {
        currencyInput.setLayerType(View.LAYER_TYPE_SOFTWARE, null); //Android does not allow dashed strokes on accelerated devices..
        currencyPickerFrom.setEventType(CurrencyEvent.Type.CHANGE_CURRENCY_FROM);
        currencyPickerTo.setEventType(CurrencyEvent.Type.CHANGE_CURRENCY_TO);
    }

    private void initialState() {
        source.getCurrencies()
                .map(RxUtils::sort)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext(this::onCurrencies));
    }

    private void onCurrencies(List<Currency> currencies) {
        String from = preferencesSource.<String>get(CURRENCY_FROM);
        String to = preferencesSource.<String>get(CURRENCY_TO);

        currencyPickerFrom.applyItems(currencies, from != null ? from : "AUD");
        currencyPickerTo.applyItems(currencies, to != null ? to : "SEK");
    }

    @OnClick(R.id.container)
    public void onClick() {
        ViewUtils.hideKeyboard(getBaseActivity());
    }

    @OnClick(R.id.goToCurrencyToday)
    public void onClickToCurrencyToday() {
        Intent intent = new Intent(getContext(), CurrencyTodayActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.icon)
    public void onClickIcon() {
        ++debugClicks;

        if (debugClicks >= 10) {
            new DebugDialogFragment().show(getFragmentManager(), DebugDialogFragment.class.getName());
            analytics.openDebug();
        }
    }

    private void setupBehavior() {
        Observable<Event> show = eventStream.stream()
                .filter(event -> event == KeyboardEvent.KEYBOARD_SHOW)
                .share();

        Observable<Event> hide = eventStream.stream()
                .filter(event -> event == KeyboardEvent.KEYBOARD_HIDE);

        show.delay(100, TimeUnit.MILLISECONDS, Schedulers.computation())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext(e -> scrollView.smoothScrollTo(0, scrollView.getChildAt(0).getBottom())));

        show.subscribe(onNext(e -> favFAB.hide()));
        hide.subscribe(onNext(e -> favFAB.show()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_currency, container, false);
    }
}
