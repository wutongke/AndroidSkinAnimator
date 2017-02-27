package com.ximsfei.skindemo.ui.discover;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;

import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.databinding.FragmentSongBinding;
import com.ximsfei.skindemo.databinding.HeaderSongItemBinding;
import com.ximsfei.skindemo.ui.adapter.SongMenuAdapter;
import com.ximsfei.skindemo.ui.base.BaseFragment;

/**
 * Created by ximsfei on 17-1-8.
 */

public class SongMenuFragment extends BaseFragment<FragmentSongBinding> {
    private HeaderSongItemBinding mHeaderBinding;
    private SongMenuAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_song;
    }

    @Override
    protected void onCreateVew(LayoutInflater inflater, Bundle savedInstanceState) {
        super.onCreateVew(inflater, savedInstanceState);
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_song_item, null, false);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mDataBinding.recyclerView.setPullRefreshEnabled(false);
        mDataBinding.recyclerView.setLoadingMoreEnabled(true);
        mDataBinding.recyclerView.addHeaderView(mHeaderBinding.getRoot());
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.recyclerView.setNestedScrollingEnabled(false);
        mDataBinding.recyclerView.setHasFixedSize(false);
        mDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new SongMenuAdapter(getActivity());
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {

    }
}
