package com.currencymerlin.juansandoval.currency_merlin.view.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.currencymerlin.juansandoval.currency_merlin.view.activity.BaseActivity;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBaseActivity().getComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    public BaseActivity getBaseActivity() {
        return ((BaseActivity) getActivity());
    }
}
