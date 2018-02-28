package com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence;


import java.util.List;

import rx.Observable;

public interface Storage<T, U> {
    void put(T t);
    void put(List<T> t);
    Observable<T> get(U id);
    Observable<List<T>> getAll();
    void invalidate();
}
