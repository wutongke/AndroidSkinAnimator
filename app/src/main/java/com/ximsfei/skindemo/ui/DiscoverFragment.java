package com.ximsfei.skindemo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.FragmentDiscoverBinding;
import com.ximsfei.skindemo.ui.adapter.TabFragmentPagerAdapter;
import com.ximsfei.skindemo.ui.base.BaseFragment;
import com.ximsfei.skindemo.ui.discover.RadioFragment;
import com.ximsfei.skindemo.ui.discover.RankingFragment;
import com.ximsfei.skindemo.ui.discover.RecommendFragment;
import com.ximsfei.skindemo.ui.discover.SongMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ximsfei on 17-1-7.
 */

public class DiscoverFragment extends BaseFragment<FragmentDiscoverBinding> {
    private TabFragmentPagerAdapter mTabFragmentPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discover;
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
        list.add(new RecommendFragment());
        list.add(new SongMenuFragment());
        list.add(new RadioFragment());
        list.add(new RankingFragment());
        List<String> listTitle = new ArrayList<>();
        listTitle.add("个性推荐");
        listTitle.add("歌单");
        listTitle.add("主播电台");
        listTitle.add("排行榜");
        mTabFragmentPagerAdapter = new TabFragmentPagerAdapter(getChildFragmentManager(), list, listTitle);
        mDataBinding.viewPager.setAdapter(mTabFragmentPagerAdapter);
        mDataBinding.tabLayout.setupWithViewPager(mDataBinding.viewPager);
    }
}
