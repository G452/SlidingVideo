package com.g2452.demo.mvp.persenter;

import com.g2452.demo.base.BasePersenter;
import com.g2452.demo.mvp.model.HomeModel;
import com.g2452.demo.mvp.model.VideoModel;
import com.g2452.demo.mvp.view.HomeView;

import java.util.Map;

/**
 * 作者：G on 2020/4/21
 * 时间：17:13
 * 概述：
 */
public class VideoPersenter extends BasePersenter<VideoModel, HomeView> {
    @Override
    protected void onViewDestory() {
        if (model != null) {
            model.stopRequest();
        }
    }

    public void getData(String url, Map<String,String> map) {
        model.getVideoModel(context, url,getView() ,map);
    }

    @Override
    public void registerView() {

    }

    @Override
    public void registerModel() {

    }
}
