package com.ximsfei.skindemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ximsfei.skindemo.R;
import com.ximsfei.skindemo.bean.RecommendItem;
import com.ximsfei.skindemo.databinding.RecommendItemBinding;
import com.ximsfei.skindemo.ui.adapter.baseadapter.BaseRecyclerViewAdapter;
import com.ximsfei.skindemo.ui.adapter.baseadapter.BaseRecyclerViewHolder;

/**
 * Created by ximsfei on 2017/1/15.
 */

public class RecommendAdapter extends BaseRecyclerViewAdapter<RecommendItem> {
    public static final int TYPE_SONG_MENU = 1;
    public static final int TYPE_UNIQUE = 2;
    public static final int TYPE_LASTEST = 3;
    public static final int TYPE_MV = 4;
    public static final int TYPE_RADIO = 5;

    public RecommendAdapter(Context context) {
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendItemViewHolder(parent, R.layout.recommend_item);
    }

    private class RecommendItemViewHolder extends BaseRecyclerViewHolder<RecommendItem, RecommendItemBinding> {
        public RecommendItemViewHolder(ViewGroup viewGroup, int layoutId) {
            super(viewGroup, layoutId);
        }

        @Override
        public void onBindViewHolder(RecommendItem item, int position) {
            mBinding.title.setText(item.title);
            Context context = mBinding.recommend1.image.getContext();
            mBinding.indicator.setImageResource(item.indicator);
            Glide.with(context).load(item.item0.image)
                    .error(R.drawable.loading)
                    .crossFade(1000)
                    .into(mBinding.recommend1.image);
            Glide.with(context).load(item.item1.image)
                    .error(R.drawable.loading)
                    .crossFade(1000)
                    .into(mBinding.recommend2.image);
            Glide.with(context).load(item.item2.image)
                    .error(R.drawable.loading)
                    .crossFade(1000)
                    .into(mBinding.recommend3.image);
            Glide.with(context).load(item.item3.image)
                    .error(R.drawable.loading)
                    .crossFade(1000)
                    .into(mBinding.recommend4.image);
            Glide.with(context).load(item.item4.image)
                    .error(R.drawable.loading)
                    .crossFade(1000)
                    .into(mBinding.recommend5.image);
            Glide.with(context).load(item.item5.image)
                    .error(R.drawable.loading)
                    .crossFade(1000)
                    .into(mBinding.recommend6.image);
            mBinding.recommend1.description.setText(item.item0.subtitle);
            mBinding.recommend2.description.setText(item.item1.subtitle);
            mBinding.recommend3.description.setText(item.item2.subtitle);
            mBinding.recommend4.description.setText(item.item3.subtitle);
            mBinding.recommend5.description.setText(item.item4.subtitle);
            mBinding.recommend6.description.setText(item.item5.subtitle);
        }
    }
}
