package com.ximsfei.skindemo.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.FragmentRadioBinding;
import com.ximsfei.skindemo.ui.base.BaseFragment;

import skin.support.animator.AnimatorType;
import skin.support.animator.SingleAnimator.SingleAnimatorUtil;

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
//        int childCount = mDataBinding.viewLayout.getChildCount();
//        for (int i = 1; i < childCount; i++) {
//            final int index = i;
//            mDataBinding.viewLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    SingleAnimatorUtil.executeAnimator(v, AnimatorType.values()[index % AnimatorType.values().length], null);
//                }
//            });
//        }
        mDataBinding.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataBinding.text1.getText().equals("text1")){
                    mDataBinding.text1.setText("text1_change");
                }else{
                    mDataBinding.text1.setText("text1");
                }
            }
        });

        mDataBinding.text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataBinding.text2.setVisibility(View.GONE);
            }
        });
    }


    @Override
    protected void loadData() {

    }
}
