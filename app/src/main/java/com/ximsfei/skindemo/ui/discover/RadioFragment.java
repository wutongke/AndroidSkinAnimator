package com.ximsfei.skindemo.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.FragmentRadioBinding;
import com.ximsfei.skindemo.ui.base.BaseFragment;

import skin.support.animator.SingleAnimator.hite.AlphaHintAnimator;
import skin.support.animator.SingleAnimator.hite.RotationHintAnimator;
import skin.support.animator.SingleAnimator.hite.ScaleHintAnimator;
import skin.support.animator.SingleAnimator.hite.TranslationAlphaHintAnimator;
import skin.support.animator.SingleAnimator.hite.TranslationAlphaHintAnimator2;
import skin.support.animator.SingleAnimator.hite.TranslationHintAnimator;
import skin.support.animator.SingleAnimator.hite.TranslationHintAnimator2;
import skin.support.animator.SingleAnimator.hite.TranslationRotationHintAnimator2;

/**
 * Created by ximsfei on 17-1-8.
 */

public class RadioFragment extends BaseFragment<FragmentRadioBinding> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_radio;
    }

    @Override
    protected void onCreateVew(LayoutInflater inflater, Bundle savedInstanceState) {
        super.onCreateVew(inflater, savedInstanceState);
        mDataBinding.recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = mDataBinding.viewLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    mDataBinding.viewLayout.getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
        });
        mDataBinding.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataBinding.text1.getText().equals("text1")) {
                    mDataBinding.text1.setText("text1_change");
                } else {
                    mDataBinding.text1.setText("text1");
                }
            }
        });

        mDataBinding.text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotationHintAnimator.getInstance().apply(mDataBinding.text2, null).start();
            }
        });

        mDataBinding.text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleHintAnimator.getInstance().apply(v, null).start();
            }
        });

        mDataBinding.text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationHintAnimator.getInstance().apply(v, null).start();
            }
        });

        mDataBinding.text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationHintAnimator2.getInstance().apply(v, null).start();
            }
        });

        mDataBinding.text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaHintAnimator.getInstance().apply(v, null).start();
            }
        });

        mDataBinding.text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationRotationHintAnimator2.getInstance().apply(v, null).start();
            }
        });

        mDataBinding.text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationAlphaHintAnimator.getInstance().apply(v, null).start();
            }
        });
        mDataBinding.text10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslationAlphaHintAnimator2.getInstance().apply(v, null).start();
            }
        });
    }


    @Override
    protected void loadData() {

    }
}
