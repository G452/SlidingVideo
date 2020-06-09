package com.g2452.demo.view.photoview;



import android.annotation.TargetApi;
import android.view.View;
/**
 * 作者：G
 * 时间：2020/6/8  10:18
 * 概述：
 */
@TargetApi(16)
public class SDK16 {

    public static void postOnAnimation(View view, Runnable r) {
        view.postOnAnimation(r);
    }

}