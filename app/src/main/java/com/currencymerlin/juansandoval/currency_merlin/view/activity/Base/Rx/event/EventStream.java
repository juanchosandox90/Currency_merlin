package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.event;

import com.currencymerlin.juansandoval.currency_merlin.BuildConfig;

public class EventStream extends AbstractEventStream<Event> {
    public EventStream() {
        super();

        stream().doOnCompleted(() -> {
            if (BuildConfig.DEBUG) {
                throw new RuntimeException("AbstractEventStream should not be completed");
            }
        });
    }
}
