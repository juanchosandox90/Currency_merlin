package com.currencymerlin.juansandoval.currency_merlin.view.activity.view.currencypicker.internal;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.SimpleViewHolder> {
    private final Context mContext;
    private List<Currency> currencies;

    public CurrencyAdapter(Context context) {
        mContext = context;
        currencies = new ArrayList<>();
        currencies.add(new Currency("INIT", 1337, java.util.Currency.getInstance(Currency.BASE_ID)));//Workaround for RecyclerPager lib & its looper
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.view_currency_picker_recycler_item, null, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.title.setText(currencies.get(position).getId());
        holder.mPosition = position;
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public void applyItems(List<Currency> currencies) {
        this.currencies = currencies;
        notifyDataSetChanged();
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public int mPosition;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }
}
