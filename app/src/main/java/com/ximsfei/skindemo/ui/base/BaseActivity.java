package com.ximsfei.skindemo.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.ximsfei.skindemo.R;

import skin.support.app.SkinCompatActivity;

/**
 * Created by ximsfei on 17-1-7.
 */

public abstract class BaseActivity<VDB extends ViewDataBinding> extends SkinCompatActivity {
    protected VDB mDataBinding;
//    private ActivityBaseBinding mBaseBinding;

//    @Override
//    public void setContentView(@LayoutRes int layoutResID) {
//        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
//        setContentView(mBaseBinding.getRoot());
//        mDataBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
//        mBaseBinding.container.addView(mDataBinding.getRoot());
//        initToolbar(mBaseBinding.toolBar);
//    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
