# Currency Merlin, a currency converter built in Android
Built on RxJava
Shows the currency in recycler view from the fixer.io API


## Features
* Cool concurrency fueled by Rx.
* Custom views and aggressive encapsulation. React -like architecture with View components.
* Material Design.
* Architecture loosely based on Fowlers Uncle Bobs Clean Architecture.
* Functional approach to the problems of asynchronisity in Android.
* Check the currency today if are not sure of the conversion. 

## Look at
* __[CurrencyActivity]:__ Doesn't look like much, it's in charge of loading the initial data and showing a loader when it's slow. Also handles Network errors in an extremely graceful way.
* __[CurrencyFragment]:__ It contains all the Views one will see when opening the app, visually speaking. Notice that the Views are implemented in their own encapsulations, like CurrencyPickerViewPager.
* __[CurrencyPickerViewPager]:__ Encapsulates the quite complex behavior behind the "pickers".
* __[CurrencySource]:__ It's the glue between View and Data. It handles offline caching, interacts with the API layer.
* __[AbstractEventStream]:__ My own adaptation of otto. EventBus that uses RxJava instead.
* __[CurrencyTodayActivity]:__ Go to the currency table and check it from the API fixer.io

## Architectural info
* __Activity__: Manages current View/Scene state, decides which View is showing when, reacts to events. Orchestrates Fragments.
* __Fragment:__ View compoents. Orchestrates Views.
* __Views:__ More encapsulation of View logic. Views are the smallest components. Orchestrates themselves.
* __Source:__ The Single Source of Truth for data, i.e. the data layer and the only entry for accessing data. Sources are the glue of this architecture.
* __Dependency Injection__: A Graph of dependencies, used by Dagger2. Provides inversion of control, a View that needs a Source is not coupled with the instantiation of the Source. The View merely asks for a CurrencySource and gets it. Singletons and hard coupling.
* __TodaysCurrency__: Manages the recycler view for check the currency of the day by the fixer.io API consumption.

Thanks to fixer.io and github.com/lsjwzh/RecyclerViewPager for the RecyclerViewPager extensions.

##### © 2018 JUAN CAMILO SANDOVAL DEVIA ALL RIGHTS RESERVED
