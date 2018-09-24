package com.example.sarah.prospectsproject.helper;

/**
 * Created by sarah on 2/09/2018.
 */

public interface ICustomSharedPreferences {

    String getString(String key);

    Integer getInt(String key);

    void addString(String key, String value);

    void addInt(String key, Integer value);

    void addBoolean(String key, Boolean value);

    Boolean getBoolean(String key);

    void deleteValue(String key);
}
