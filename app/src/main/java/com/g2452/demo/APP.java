package com.g2452.demo;

import android.app.Application;
import android.content.Context;
import android.net.Uri;

import com.danikula.videocache.HttpProxyCacheServer;
import com.danikula.videocache.file.FileNameGenerator;
import com.g2452.demo.http.HttpUtlis;
import com.g2452.demo.util.LogUtil;

import java.io.File;

/**
 * 作者：G on 2020/4/21
 * 时间：16:38
 * 概述：
 */
public class APP extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext() {
        return context;
    }
}
