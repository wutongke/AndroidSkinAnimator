package com.ximsfei.skindemo.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.FragmentRadioBinding;
import com.ximsfei.skindemo.ui.base.BaseFragment;
import com.ximsfei.skindemo.widget.ArrayDialogFragment;

import java.util.ArrayList;
import java.util.List;

import skin.support.animator.Action;
import skin.support.animator.SingleAnimator.AnimatorConfig;
import skin.support.animator.SingleAnimator.AnimatorManager;
import skin.support.animator.SingleAnimator.ViewAnimatorType;

/**
 * Created by ximsfei on 17-1-8.
 */

public class RadioFragment extends BaseFragment<FragmentRadioBinding> {

    private ArrayDialogFragment arrayDialogFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_radio;
    }

    @Override
    protected void onCreateVew(LayoutInflater inflater, Bundle savedInstanceState) {
        super.onCreateVew(inflater, savedInstanceState);
        initAnimatorChooseDialog();
        initClick();
    }

    private void initAnimatorChooseDialog() {
        List<String> itemList = new ArrayList<>();
        for (ViewAnimatorType animatorType : ViewAnimatorType.values()) {
            itemList.add(animatorType.name());
        }
        arrayDialogFragment = new ArrayDialogFragment();
        arrayDialogFragment.setItemList(itemList);
        arrayDialogFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AnimatorManager.setConfig(new AnimatorConfig.Builder()
                        .textviewVisibleAnimationType(ViewAnimatorType.values()[position])
                        .textviewTextAnimationType(ViewAnimatorType.AlphaUpdateAnimator)
                        .imageviewVisibleAnimationType(ViewAnimatorType.values()[position])
                        .build());
                if (isAnimatorOpen()) {
                    AnimatorManager.openAnimator();
                } else {
                    AnimatorManager.closeAnimator();
                }
            }
        });
    }

    private void initClick() {
        mDataBinding.recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = mDataBinding.viewLayout.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    mDataBinding.viewLayout.getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
        });

        mDataBinding.choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayDialogFragment.show(getActivity().getSupportFragmentManager(), "");
            }
        });

        mDataBinding.animator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int childCount = mDataBinding.viewLayout.getChildCount();
                int textViewOffset = mDataBinding.viewLayout.indexOfChild(mDataBinding.text2);
                for (int i = textViewOffset; i < childCount; i++) {
                    mDataBinding.viewLayout.getChildAt(i).setVisibility(View.GONE);
                }
            }
        });

        mDataBinding.toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().equals("close")) {
                    mDataBinding.toggle.setText("关闭全局动画");
                    v.setTag("open");
                    AnimatorManager.openAnimator();
                } else {
                    mDataBinding.toggle.setText("开启全局动画");
                    v.setTag("close");
                    AnimatorManager.closeAnimator();
                }
            }
        });

        final int textviewOffset = mDataBinding.viewLayout.indexOfChild(mDataBinding.text2);
        int childCount = mDataBinding.viewLayout.getChildCount();
        for (int i = textviewOffset; i < childCount; i++) {
            final int index = i;
            mDataBinding.viewLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAnimatorOpen()) {
                        Toast.makeText(mDataBinding.viewLayout.getContext(), "开启全局动画后不执行点击动画效果", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        ViewAnimatorType.values()[(index - textviewOffset) % ViewAnimatorType.values().length]
                                .apply(mDataBinding.viewLayout.getChildAt(index), new Action() {
                                    @Override
                                    public void action() {
                                        mDataBinding.viewLayout.getChildAt(index).setVisibility(View.GONE);
                                    }
                                });
                    }
                }
            });
        }

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
    }

    private boolean isAnimatorOpen() {
        return mDataBinding.toggle.getTag().equals("open");
    }

    @Override
    protected void loadData() {

    }
}
