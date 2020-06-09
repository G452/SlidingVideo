package com.g2452.demo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.g2452.demo.R;
import com.g2452.demo.view.EmptyControlVideo;

/**
 * 作者：G
 * 时间：2020/6/9  14:25
 * 概述：
 */

public class Video5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video5);
        EmptyControlVideo mEmptyControlVideo1 = (EmptyControlVideo) findViewById(R.id.video1);
        EmptyControlVideo mEmptyControlVideo2 = (EmptyControlVideo) findViewById(R.id.video2);
        mEmptyControlVideo1.setUp("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689143613AD7CC47.mp4", true, "");
        mEmptyControlVideo2.setUp("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689143319AEAA87F.mp4", true, "");
        mEmptyControlVideo1.setVideoImg("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689143613AD7CC47.mp4");
        mEmptyControlVideo2.setVideoImg("http://vicdn.baichuan99.cn/user/202004/12/18/59/1586689143319AEAA87F.mp4");
        mEmptyControlVideo1.startPlayLogic();
        mEmptyControlVideo2.startPlayLogic();
    }
}
