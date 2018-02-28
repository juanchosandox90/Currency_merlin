package com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.application;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.view.ViewComponent;
import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.dependency.view.ViewModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, SourceModule.class, ApiModule.class})
public interface ApplicationComponent {
    ViewComponent plus(ViewModule module);
}
