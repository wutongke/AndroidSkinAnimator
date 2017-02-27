package com.ximsfei.skindemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.ActivityAboutSkinLibBinding;
import com.ximsfei.skindemo.ui.base.BaseActivity;

/**
 * Created by ximsfei on 2017/1/17.
 */

public class AboutSkinLibActivity extends BaseActivity<ActivityAboutSkinLibBinding> {
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about_skin_lib;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar(mDataBinding.toolBar);
    }
}
