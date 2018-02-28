package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.subscriber;


import com.google.firebase.crash.FirebaseCrash;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observers.Subscribers;

public class SubscriberUtils {
    public static <T> Subscriber<T> onNext(Action1<T> onNext) {
        return Subscribers.create(wrap(onNext), onError());
    }

    public static <T> Subscriber<T> onError(Action1<Throwable> onError) {
        return Subscribers.create(e -> {}, wrap(onError));
    }

    public static <T> Subscriber<T> onComplete(Action0 onComplete) {
        return Subscribers.create(e -> {}, onError(), onComplete);
    }

    public static <T> Subscriber<T> onNextOnComplete(Action1<T> onNext, Action0 onComplete) {
        return Subscribers.create(wrap(onNext), onError(), onComplete);
    }

    public static <T> Subscriber<T> onNextOnError(Action1<T> onNext, Action1<Throwable> onError) {
        return Subscribers.create(wrap(onNext), wrap(onError), () -> {});
    }

    private static Action1<Throwable> onError() {
        return Throwable::printStackTrace;
    }

    /**
     * Wrap a function in try catch to be sure to catch
     * all possible errors!
     */
    private static <T> Action1<T> wrap(Action1<T> toWrap) {
        return e -> {
            try {
                toWrap.call(e);
            } catch (Exception ex) {
                FirebaseCrash.report(ex);
            }
        };
    }
}
