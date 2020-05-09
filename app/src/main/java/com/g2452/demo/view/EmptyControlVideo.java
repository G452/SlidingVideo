package com.g2452.demo.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.g2452.demo.R;
import com.g2452.demo.util.LogUtil;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import moe.codeest.enviews.ENPlayView;

/**
 * * 作者：G
 * * 时间：2020/4/24  17:04
 * 无任何控制ui的播放
 * Created by guoshuyu on 2017/8/6.
 */

public class EmptyControlVideo extends StandardGSYVideoPlayer {

    private ImageView mVideoImg;
    private ENPlayView mPauseImg;
    private RelativeLayout mVideoRelativeLayout;

    public EmptyControlVideo(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public EmptyControlVideo(Context context) {
        super(context);
    }

    public EmptyControlVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.empty_control_video;
    }

    @Override
    protected void init(Context context) {
        super.init(context);
        mVideoImg = (ImageView) findViewById(R.id.video_img);
        mPauseImg = (ENPlayView) findViewById(R.id.start);
    }

    @Override
    protected void touchSurfaceMoveFullLogic(float absDeltaX, float absDeltaY) {
        super.touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
        //不给触摸快进，如果需要，屏蔽下方代码即可
        mChangePosition = false;

        //不给触摸音量，如果需要，屏蔽下方代码即可
        mChangeVolume = false;

        //不给触摸亮度，如果需要，屏蔽下方代码即可
        mBrightness = false;
    }

    @Override
    protected void touchDoubleUp() {
//        super.touchDoubleUp();
        //不需要双击暂停
    }

    /*设置封面图*/
    public void setVideoImg(String url) {
        Glide.with(this).load(url).into(mVideoImg);
    }


}
