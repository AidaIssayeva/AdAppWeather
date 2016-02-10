package com.videri.core.preference;

/**
 * Created by yiminglin on 8/20/15.
 */

/**
 * The PreferenceStorage class stores information in SharedPreferences.
 * This is intended for internal use and is not fully supported.
 * If you use PreferenceStorage it may cause undesired behavior.
 */
public interface PreferenceStorage {

    void storeData(String key, String value);

    String getData(String key);


}
