package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.subscriber.SubscriberUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class AbstractEventStream<T> {
    private PublishSubject<T> stream = PublishSubject.create();
    private Observable<T> historyStream;
    private BehaviorSubject<T> mostRecentStream;

    @Inject
    public AbstractEventStream() {
        stream.subscribe(SubscriberUtils.onNextOnComplete(next -> mostRecentStream.onNext(next), () -> mostRecentStream.onCompleted()));
        historyStream = stream().replay().autoConnect();
        mostRecentStream = BehaviorSubject.create();

        historyStream().subscribe(SubscriberUtils.onComplete(() -> {
            //just subscribe to init stream
        }));
    }

    public void post(T t) {
        stream.onNext(t);
    }

    public Observable<T> stream() {
        return stream;
    }

    /**
     * @return an Observable that repeats the EventStreams events (look at Observable.repeat)
     */
    public Observable<T> historyStream() {
        return historyStream;
    }
}
