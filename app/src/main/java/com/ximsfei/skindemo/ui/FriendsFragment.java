package com.ximsfei.skindemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.FragmentFriendsBinding;
import com.ximsfei.skindemo.ui.adapter.TabFragmentPagerAdapter;
import com.ximsfei.skindemo.ui.base.BaseFragment;
import com.ximsfei.skindemo.ui.friends.ContactsFragment;
import com.ximsfei.skindemo.ui.friends.FeedsFragment;
import com.ximsfei.skindemo.ui.friends.NearbyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ximsfei on 17-1-7.
 */

public class FriendsFragment extends BaseFragment<FragmentFriendsBinding> {
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friends;
    }

    @Override
    protected void onCreateVew(LayoutInflater inflater, Bundle savedInstanceState) {
        super.onCreateVew(inflater, savedInstanceState);
        configFragments();
    }

    @Override
    protected void loadData() {

    }

    private void configFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FeedsFragment());
        list.add(new NearbyFragment());
        list.add(new ContactsFragment());
        List<String> listTitle = new ArrayList<>();
        listTitle.add("动态");
        listTitle.add("附近");
        listTitle.add("好友");
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), list, listTitle);
        mDataBinding.viewPager.setAdapter(mTabFragmentPagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }
}
