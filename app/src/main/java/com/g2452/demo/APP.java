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

//    private HttpProxyCacheServer proxy;
//
//    public static HttpProxyCacheServer getProxy(Context context) {
//        APP app = (APP) context.getApplicationContext();
//        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
//    }

//    private HttpProxyCacheServer newProxy() {
//        return new HttpProxyCacheServer.Builder(this)
//                .maxCacheSize(1024 * 1024 * 1024)
//                .cacheDirectory(getVideoCacheDir(this))
//                .fileNameGenerator(new MyFileNameGenerator())
//                .build();
//    }
//
//    /*设置缓存文件目录*/
//    private File getVideoCacheDir(Context context) {
//        return new File(context.getExternalFilesDir("myvideo"), "audio-cache");
//    }
//
//    /*缓存重命名规则*/
//    public class MyFileNameGenerator implements FileNameGenerator {
//        public String generate(String url) {
//            return url;
//        }
//    }

}
