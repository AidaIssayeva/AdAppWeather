package com.videri.core.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;
import java.util.Set;


/**
 * Created by yiminglin on 8/20/15.
 */
public class PreferenceStorageImpl implements PreferenceStorage {

    private final static String KEY_PREFIX = "VDR__";

    private SharedPreferences sharedPreferences;


    public PreferenceStorageImpl(String type, Context context) {
        sharedPreferences = context.getSharedPreferences(type, Context.MODE_PRIVATE);
    }

    PreferenceStorageImpl(String type, SharedPreferences prefs) {
        sharedPreferences = prefs;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressLint("CommitPrefEdits")
    public void storeData(String key, String value) {
        Editor edit = sharedPreferences.edit();
        edit.putString(KEY_PREFIX + key, value);
        commitEditor(edit);
    }

    @SuppressLint("NewApi")
    private void commitEditor(Editor edit) {
        edit.commit();
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
//            edit.commit();
//        }
//        else {
//            edit.apply();
//        }
    }

    /**
     * {@inheritDoc}
     */
    public String getData(String key) {
        return sharedPreferences.getString(KEY_PREFIX + key, null);
    }




}