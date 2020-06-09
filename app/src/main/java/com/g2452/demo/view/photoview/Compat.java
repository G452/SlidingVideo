package com.g2452.demo.view.photoview;


import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
/**
 * 作者：G
 * 时间：2020/6/8  10:17
 * 概述：
 */
public class Compat {

    private static final int SIXTY_FPS_INTERVAL = 1000 / 60;

    public static void postOnAnimation(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            SDK16.postOnAnimation(view, runnable);
        } else {
            view.postDelayed(runnable, SIXTY_FPS_INTERVAL);
        }
    }

}