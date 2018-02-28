package com.currencymerlin.juansandoval.currency_merlin.view.activity;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event.Event;

public class CurrencyEvent<E> implements Event {
    public enum Type {
        CHANGE_CURRENCY_FROM, CHANGE_CURRENCY_TO, CHANGE_AMOUNT
    }

    public CurrencyEvent(E value, Type type) {
        this.value = value;
        this.type = type;
    }

    public final E value;
    public final Type type;

    @Override
    public String toString() {
        return String.format("{type: %s, value: %s}", type.name(), value.toString());
    }
}
