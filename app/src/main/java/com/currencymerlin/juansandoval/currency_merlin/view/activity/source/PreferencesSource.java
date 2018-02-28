package com.currencymerlin.juansandoval.currency_merlin.view.activity.source;


import com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.preferences.PersistentStorage;

public class PreferencesSource {
    private PersistentStorage storage;

    public PreferencesSource(PersistentStorage storage) {
        this.storage = storage;
    }

    public   <T> void put(String key, T value) {
        storage.put(key, value);
    }

    public <T> T get(String key) {
        return storage.get(key);
    }
}
