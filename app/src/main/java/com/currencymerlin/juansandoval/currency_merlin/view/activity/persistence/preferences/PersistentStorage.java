package com.currencymerlin.juansandoval.currency_merlin.view.activity.persistence.preferences;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.currencymerlin.juansandoval.currency_merlin.view.activity.Base.Rx.CurrencyApplication;
import com.google.gson.Gson;

import javax.inject.Inject;

public class PersistentStorage {
    public static final String STORAGE_KEY = "GENERAL_STORAGE";
    private static final String DELIMITER = "::";

    private SharedPreferences shared;
    private Gson gson;

    @Inject
    public PersistentStorage(CurrencyApplication context, Gson gson) {
        this.gson = gson;
        this.shared = context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE);
    }

    public <T> boolean put(String key, T value) {
        return shared.edit().putString(key, value.getClass().getName() + DELIMITER + gson.toJson(value)).commit();
    }

    @Nullable
    public <T> T get(String key) {
        String raw = shared.getString(key, null);

        if (raw != null) {
            String classString = raw.substring(0, raw.indexOf(DELIMITER));
            String json = raw.substring(raw.indexOf(DELIMITER) + DELIMITER.length());

            try {
                Class<T> clazz = (Class<T>) Class.forName(classString);

                return gson.fromJson(json, clazz);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    public boolean invalidate() {
        return shared.edit().clear().commit();
    }

    public boolean invalidate(String key) {
        return shared.edit().remove(key).commit();
    }
}
