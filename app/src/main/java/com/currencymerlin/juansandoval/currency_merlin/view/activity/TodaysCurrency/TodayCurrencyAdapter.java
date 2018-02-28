package com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.currencymerlin.juansandoval.currency_merlin.R;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.TodaysCurrency.model.CurrencyToday;

import java.util.List;

public class TodayCurrencyAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<CurrencyToday> currencyList;

    public TodayCurrencyAdapter(Context context, List<CurrencyToday> currencyList) {
        this.context = context;
        this.currencyList = currencyList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return currencyList.size();
    }

    @Override
    public Object getItem(int position) {
        return currencyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currencyItemView = layoutInflater.inflate(R.layout.currency_item, null);
        TextView tvName = (TextView) currencyItemView.findViewById(R.id.tvName);
        TextView tvRate = (TextView) currencyItemView.findViewById(R.id.tvRate);

        final CurrencyToday c = currencyList.get(position);
        tvName.setText(c.getName());
        tvRate.setText(Double.toString(c.getRate()));
        return currencyItemView;
    }
}
