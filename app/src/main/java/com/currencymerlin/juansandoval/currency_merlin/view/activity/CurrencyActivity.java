package com.currencymerlin.juansandoval.currency_merlin.view.activity;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.EventStream;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.subscriber.SubscriberUtils;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.analytics.BehaviorTracker;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.fragment.CurrencyFragment;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.fragment.SplashFragment;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.source.CurrencySource;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;

public class CurrencyActivity extends BaseActivity {
    @Inject
    CurrencySource source;
    @Inject
    EventStream eventStream;
    @Inject
    BehaviorTracker behaviorTracker;

    @BindView(android.R.id.content)
    View content;
    @BindView(R.id.fragmentContainer)
    View fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showFragment(new SplashFragment());

        initialState();
        analytics();
    }

    private void initialState() {
        source.getCurrencies()
                .delay(300, TimeUnit.MILLISECONDS) //give that splash some time to show
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SubscriberUtils.onNextOnError(e -> showFragmentTransitionIcon(new CurrencyFragment(), fragmentContainer.findViewById(R.id.icon)), this::onNetworkError));
    }

    private void onNetworkError(Throwable throwable) {
        Snackbar.make(content, "No internet connection", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", e -> {
                    initialState();
                })
                .show();
    }

    private void analytics() {
        behaviorTracker.start();
    }

    @Override
    protected void inject() {
        getComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected View getRootView() {
        return fragmentContainer;
    }
}
