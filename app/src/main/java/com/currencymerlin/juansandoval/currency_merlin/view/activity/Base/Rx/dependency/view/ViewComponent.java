package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.view;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.BaseActivity;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.CurrencyActivity;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.fragment.BaseFragment;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.fragment.CurrencyFragment;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.CurrencyInput;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.CurrencyPickerViewPager;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.CurrencyTextView;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.FavoriteFAB;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.view.currencypicker.internal.CurrencyPickerRecycler;

import dagger.Subcomponent;

@Subcomponent(modules = ViewModule.class)
public interface ViewComponent {
    void inject(BaseActivity activity);
    void inject(CurrencyActivity activity);

    void inject(BaseFragment fragment);
    void inject(CurrencyFragment fragment);

    void inject(CurrencyPickerViewPager view);
    void inject(CurrencyPickerRecycler view);
    void inject(CurrencyTextView view);
    void inject(CurrencyInput view);
    void inject(FavoriteFAB view);
}
