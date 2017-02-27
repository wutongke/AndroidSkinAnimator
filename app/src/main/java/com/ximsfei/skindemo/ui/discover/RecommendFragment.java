package com.ximsfei.skindemo.ui.discover;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.bean.ImageItem;
import com.ximsfei.skindemo.bean.RecommendItem;
import com.ximsfei.skindemo.databinding.FooterRecommendItemBinding;
import com.ximsfei.skindemo.databinding.FragmentRecommendBinding;
import com.ximsfei.skindemo.databinding.HeaderRecommendItemBinding;
import com.ximsfei.skindemo.ui.adapter.RecommendAdapter;
import com.ximsfei.skindemo.ui.base.BaseFragment;
import com.ximsfei.skindemo.utils.TimeUtil;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import static com.ximsfei.skindemo.DataManager.BANNER_IMAGES;
import static com.ximsfei.skindemo.DataManager.IMAGES;
import static com.ximsfei.skindemo.DataManager.LABELS;
import static com.ximsfei.skindemo.DataManager.LABELS_INDICATOR;
import static com.ximsfei.skindemo.DataManager.SUBTITLES;
import static com.ximsfei.skindemo.DataManager.TITLES;

/**
 * Created by ximsfei on 17-1-8.
 */

public class RecommendFragment extends BaseFragment<FragmentRecommendBinding> {
    private ArrayList<Integer> mBannerImages = new ArrayList<>();
    private HeaderRecommendItemBinding mHeaderBinding;
    private FooterRecommendItemBinding mFooterBinding;
    private RecommendAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void onCreateVew(LayoutInflater inflater, Bundle savedInstanceState) {
        super.onCreateVew(inflater, savedInstanceState);
        mHeaderBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.header_recommend_item, null, false);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mDataBinding.recyclerView.setPullRefreshEnabled(false);
        mDataBinding.recyclerView.setLoadingMoreEnabled(true);
        mDataBinding.recyclerView.addHeaderView(mHeaderBinding.getRoot());
        mFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.footer_recommend_item, null, false);
        mDataBinding.recyclerView.setFootView(mFooterBinding.getRoot());
        mDataBinding.recyclerView.setNoMore(true);
        mFooterBinding.getRoot().setVisibility(View.VISIBLE);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mDataBinding.recyclerView.setNestedScrollingEnabled(false);
        mDataBinding.recyclerView.setHasFixedSize(false);
        mDataBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecommendAdapter(getActivity());
        mDataBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        loadLocalData();
        loadBannerAd();
        load();
    }

    private void loadLocalData() {
        mHeaderBinding.dailyTxt.setText(getTodayTime().get(2).indexOf("0") == 0
                ? getTodayTime().get(2).replace("0", "")
                : getTodayTime().get(2));
    }

    private ArrayList<String> getTodayTime() {
        String data = TimeUtil.getData();
        String[] split = data.split("-");
        String year = split[0];
        String month = split[1];
        String day = split[2];
        ArrayList<String> list = new ArrayList<>();
        list.add(year);
        list.add(month);
        list.add(day);
        return list;
    }

    private void loadBannerAd() {
        mBannerImages.clear();
        for (int i = 0; i < BANNER_IMAGES.length; i++) {
            mBannerImages.add(BANNER_IMAGES[i]);
        }

        mHeaderBinding.banner
                .setDelayTime(5000)
                .setImages(mBannerImages)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path)
                                .crossFade(1000)
                                .into(imageView);
                    }
                }).start();
    }

    private void load() {
        mAdapter.clear();
        List<RecommendItem> list = new ArrayList<>();
        for (int i = 0; i < LABELS.length; i++) {
            RecommendItem item = new RecommendItem();
            item.title = LABELS[i];
            item.indicator = LABELS_INDICATOR[i];
            item.item0 = new ImageItem(TITLES[i * 6], SUBTITLES[i * 6], IMAGES[i * 6]);
            item.item1 = new ImageItem(TITLES[i * 6 + 1], SUBTITLES[i * 6 + 1], IMAGES[i * 6 + 1]);
            item.item2 = new ImageItem(TITLES[i * 6 + 2], SUBTITLES[i * 6 + 2], IMAGES[i * 6 + 2]);
            item.item3 = new ImageItem(TITLES[i * 6 + 3], SUBTITLES[i * 6 + 3], IMAGES[i * 6 + 3]);
            item.item4 = new ImageItem(TITLES[i * 6 + 4], SUBTITLES[i * 6 + 4], IMAGES[i * 6 + 4]);
            item.item5 = new ImageItem(TITLES[i * 6 + 5], SUBTITLES[i * 6 + 5], IMAGES[i * 6 + 5]);
            list.add(item);
        }
        mAdapter.addAll(list);
    }
}
