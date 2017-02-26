package com.ximsfei.dynamicskindemo.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ximsfei.dynamicskindemo.R;

import java.util.ArrayList;

import skin.support.SkinCompatManager;
import skin.support.animator.ActivityAnimatorType;
import skin.support.animator.activityAnimator.SkinActivityAnimator;
import skin.support.utils.SkinPreference;

/**
 * Created by erfli on 2/26/17.
 */

public class ArrayDialogFragment extends DialogFragment{

    private ListView animatorChooseListView;
    private ArrayList<String> itemList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList.clear();
        itemList.add("alpha");
        itemList.add("rotate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animator_choose_fragment, null);
        animatorChooseListView = (ListView) view.findViewById(R.id.animator_choose_listview);
        ArrayAdapter<String> arrayAdapter  = new ArrayAdapter<>(view.getContext(), R.layout.animator_choose_item);
        arrayAdapter.addAll(itemList);

        animatorChooseListView.setAdapter(arrayAdapter);
        animatorChooseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SkinActivityAnimator.configActivityAnimatorType(ActivityAnimatorType.values()[position]);
                if (TextUtils.isEmpty(SkinPreference.getInstance().getSkinName())) {
                    SkinCompatManager.getInstance().loadSkin("red.skin", null);
                } else {
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                }
                ArrayDialogFragment.this.dismiss();
            }
        });
        return view;
    }
}
