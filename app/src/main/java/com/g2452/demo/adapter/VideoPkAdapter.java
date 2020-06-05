package com.g2452.demo.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.g2452.demo.R;
import com.g2452.demo.base.BaseRecycleAdapter;
import com.g2452.demo.base.BaseViewHolder;
import com.g2452.demo.bean.VideoBean;
import com.g2452.demo.view.EmptyControlVideo;

/**
 * 作者：G
 * 时间：2020/4/22  18:23
 * 概述：
 */
public class VideoPkAdapter extends BaseRecycleAdapter<VideoBean> {

    private Context mContext;
    public BaseViewHolder holder;
    public boolean isFirst = true;
    private OnFirstPlayLintener linter;

    public VideoPkAdapter(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_item2;
    }

    @Override
    protected void convert(BaseViewHolder holder, VideoBean videoBean, int postion) {
        this.holder = holder;
        RelativeLayout relat_layout = (RelativeLayout) holder.getView(R.id.relat_layout);
        EmptyControlVideo video_layout_video = (EmptyControlVideo) holder.getView(R.id.video_layout_video);
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) relat_layout.getLayoutParams();
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = displayMetrics.heightPixels/2-50;
        relat_layout.setLayoutParams(layoutParams);
        //增加封面
        video_layout_video.setVideoImg(videoBean.getVideoUrl());
        video_layout_video.setUp(videoBean.getVideoUrl(),true,"");
        video_layout_video.setLooping(true);
        /*首次播放*/
        if (isFirst && postion == 0) {
            linter.onItem(holder);
            isFirst = false;
        }
    }

    //传递接口
    public void setFirstPlaykLinter(OnFirstPlayLintener linter) {
        this.linter = linter;
    }

//自定义点击事件的  接口**
    public interface OnFirstPlayLintener {
        void onItem(BaseViewHolder holder);
    }


}
