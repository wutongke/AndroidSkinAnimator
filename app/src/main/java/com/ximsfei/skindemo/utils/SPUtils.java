package com.ximsfei.skindemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import skin.support.SkinCompatManager;

/**
 * Created by ximsfei on 17-1-8.
 */

public class SPUtils {
    private static final String FILE_NAME = "meta-data";

    private static final String KEY_MODE_NIGHT = "mode-night";
    private static final String KEY_CUR_SKIN = "cur-skin";
    private static SPUtils sInstance;
    private final Context mApp;
    private final SharedPreferences mPref;
    private final SharedPreferences.Editor mEditor;

    public static void init(Context context) {
        if (sInstance == null) {
            synchronized (SPUtils.class) {
                if (sInstance == null) {
                    sInstance = new SPUtils(context.getApplicationContext());
                }
            }
        }
    }

    public static SPUtils getInstance() {
        return sInstance;
    }

    private SPUtils(Context applicationContext) {
        mApp = applicationContext;
        mPref = mApp.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public SPUtils setNightMode(boolean nightMode) {
        mEditor.putBoolean(KEY_MODE_NIGHT, nightMode);
        return this;
    }

    public boolean getNightMode() {
        return mPref.getBoolean(KEY_MODE_NIGHT, false);
    }

    public void commitEditor() {
        mEditor.apply();
    }

    public SPUtils setCurSkin(String curSkinName) {
        mEditor.putString(KEY_CUR_SKIN, curSkinName);
        return this;
    }

    public String getCurSkin() {
        return mPref.getString(KEY_CUR_SKIN, "");
    }
}