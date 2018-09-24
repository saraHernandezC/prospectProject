package com.example.sarah.prospectsproject.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by sarah on 2/09/2018.
 */

public class CustomSharedPreferences implements ICustomSharedPreferences {

    private final SharedPreferences sharedPreferences;

    public CustomSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getString(String key) {
        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getString(key, null);
        }
        return null;
    }

    @Override
    public Integer getInt(String key) {
        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getInt(key, Constants.EMPTY_INT);
        }
        return null;
    }

    @Override
    public void addString(String key, String value) {
        if (value == null) {
            deleteValue(key);
        } else {
            addValue(key, value);
        }
    }

    @Override
    public void addInt(String key, Integer value) {
        if (value == null) {
            deleteValue(key);
        } else {
            addValue(key, value);
        }
    }

    @Override
    public void addBoolean(String key, Boolean value) {
        if (value == null) {
            deleteValue(key);
        } else {
            addValue(key, value);
        }
    }

    @Override
    public Boolean getBoolean(String key) {
        if (sharedPreferences.contains(key)) {
            return sharedPreferences.getBoolean(key,Constants.FALSE);
        }
        return Constants.FALSE;
    }


    @Override
    public void deleteValue(String key) {
        sharedPreferences.edit().remove(key).apply();
    }


    private void addValue(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    private void addValue(String key, Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    private void addValue(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private void addValue(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key, value).apply();
    }
}
