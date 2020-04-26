package com.g2452.demo.adapter;

import android.content.Context;

import com.g2452.demo.R;
import com.g2452.demo.base.BaseRecycleAdapter;
import com.g2452.demo.base.BaseViewHolder;
import com.g2452.demo.bean.HomeBean;

/**
 * 作者：G
 * 时间：2020/4/22  17:27
 * 概述：
 */
public class HomeAdapter extends BaseRecycleAdapter<HomeBean.NewslistBean> {

    private Context mContext;

    public HomeAdapter(Context mcontext) {
        super(mcontext);
        this.mContext=mcontext;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_list_item;
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeBean.NewslistBean newslistBean, int postion) {
        holder.setText(R.id.ttt_item,newslistBean.getTitle());
        holder.setUrlImage(R.id.item_img,newslistBean.getPicUrl());
    }
}
