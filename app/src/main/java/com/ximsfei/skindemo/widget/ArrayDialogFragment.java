package com.ximsfei.skindemo.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ximsfei.skindemo.R;

import java.util.ArrayList;

import skin.support.animator.Action;
import skin.support.animator.ActivityAnimatorType;
import skin.support.animator.activityAnimator.SkinActivityAnimator;

/**
 * Created by erfli on 2/26/17.
 */

public class ArrayDialogFragment extends DialogFragment {

    private ListView animatorChooseListView;
    private ArrayList<String> itemList = new ArrayList<>();

    private Action action;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemList.clear();
        for (ActivityAnimatorType activityAnimatorType : ActivityAnimatorType.values()) {
            itemList.add(activityAnimatorType.name());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animator_choose_fragment, null);
        animatorChooseListView = (ListView) view.findViewById(R.id.animator_choose_listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(view.getContext(), R.layout.animator_choose_item);
        arrayAdapter.addAll(itemList);

        animatorChooseListView.setAdapter(arrayAdapter);
        animatorChooseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SkinActivityAnimator.configActivityAnimatorType(ActivityAnimatorType.values()[position]);
                if(action != null){
                    action.action();
                }
                ArrayDialogFragment.this.dismiss();
            }
        });
        return view;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
