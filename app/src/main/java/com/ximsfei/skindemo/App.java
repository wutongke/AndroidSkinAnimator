package com.ximsfei.skindemo;

import android.app.Application;

import com.ximsfei.skindemo.utils.SPUtils;

import skin.support.SkinCompatManager;
import skin.support.design.SkinMaterialManager;

/**
 * Created by ximsfei on 17-1-8.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinMaterialManager.init(this);
        SkinCompatManager.init(this).loadSkin();
        SPUtils.init(this);
    }
}
