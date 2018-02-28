package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx;


import java.util.Collections;
import java.util.List;

public class RxUtils {
    public static <T extends Comparable> List<T> sort(List<T> list) {
        Collections.sort(list);
        return list;
    }
}
