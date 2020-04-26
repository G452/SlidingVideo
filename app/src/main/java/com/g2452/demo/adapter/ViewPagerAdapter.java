package com.g2452.demo.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.g2452.demo.R;
import com.g2452.demo.bean.VideoBean;
import com.g2452.demo.view.EmptyControlVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者：G
 * 时间：2020/4/26  10:00
 * 概述：
 */
public class ViewPagerAdapter extends PagerAdapter {


    private   Context mContext;
    private List<VideoBean> list = new ArrayList<>();
    public boolean isFirst = true;
    public ViewPagerAdapter(Context context) {
        this.mContext = context;
    }


    public void setList(List<VideoBean> datas) {
        this.list.clear();
        this.list.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.video_item2,null);
        RelativeLayout relat_layout = (RelativeLayout) view.findViewById(R.id.relat_layout);
        EmptyControlVideo video_layout_video = (EmptyControlVideo) view.findViewById(R.id.video_layout_video);
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relat_layout.getLayoutParams();
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = displayMetrics.heightPixels;
        relat_layout.setLayoutParams(layoutParams);
        video_layout_video.setVideoImg(list.get(position).getVideoUrl());
        if (isFirst && position == 0) {
            video_layout_video.release();
            video_layout_video.setUp(list.get(position).getVideoUrl(), true, "");
            video_layout_video.setLooping(true);
            video_layout_video.startPlayLogic();
            isFirst = false;
        }
        container.addView(view);    //这一步很重要
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
