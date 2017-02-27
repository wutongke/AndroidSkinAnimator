package com.ximsfei.skindemo.ui.adapter.baseadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by ximsfei on 2017/1/15.
 */
public abstract class BaseRecyclerViewHolder<T, D extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public D mBinding;

    public BaseRecyclerViewHolder(ViewGroup viewGroup, int layoutId) {
        super(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false).getRoot());
        mBinding = DataBindingUtil.getBinding(this.itemView);
    }

    /**
     * @param bean     the data of bind
     * @param position the item position of recyclerView
     */
    public abstract void onBindViewHolder(T bean, final int position);

    /**
     * 当数据改变时，binding会在下一帧去改变数据，如果我们需要立即改变，就去调用executePendingBindings方法。
     */
    void onBaseBindViewHolder(T object, final int position) {
        onBindViewHolder(object, position);
        mBinding.executePendingBindings();
    }
}
