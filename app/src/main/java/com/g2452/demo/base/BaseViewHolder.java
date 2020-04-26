package com.g2452.demo.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

/**
 * 作者：G
 * 时间：2020/4/22  17:18
 * 概述：
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private SparseArray<View> views = new SparseArray<>();
    View rootView;

    public BaseViewHolder(@NonNull View itemView, Context mcontext) {
        super(itemView);
        this.mContext = mcontext;
        rootView = itemView;

    }


    public <T extends View> T getView(int viewId) {
        T view = (T) views.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public BaseViewHolder setText(int viewId, String msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(msg);
        return this;
    }
    public BaseViewHolder setFromHtml(int viewId, String msg) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setText(msg);
        return this;
    }

    public BaseViewHolder setImageResource(int viewId, int resource) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageResource(resource);
        return this;
    }

    //设置文本是否显示
    public BaseViewHolder setVisibilityText(int viewId, int resource) {
        TextView textView = (TextView) getView(viewId);
        textView.setVisibility(resource);
        return this;
    }

    public BaseViewHolder setUrlImage(int viewId, String url) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(url).into(view);
        return this;
    }

    public void setClick(View.OnClickListener listener, int... ids) {
        if (ids == null) {
            return;
        }
        for (int id : ids) {
            getView(id).setOnClickListener(listener);
        }
    }

    public View getRootView() {
        return rootView;
    }
    //    public BaseViewHolder setImageUrl(int viewId, String url) {
//        ImageView imageView = (ImageView) getView(viewId);
//        Picasso.with(mContext).load(url).fit().into(imageView);
//        return this;
//    }

    //设置文本颜色
    public BaseViewHolder setTextColor(int viewId, int colorid) {
        TextView txtview = (TextView) getView(viewId);
        txtview.setTextColor(colorid);
        return this;
    }

    //如果想添加其他的方法请在下方添加参照setText如获取网络图片等....

}
