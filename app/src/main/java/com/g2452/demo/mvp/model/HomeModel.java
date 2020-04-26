package com.g2452.demo.mvp.model;

import android.content.Context;

import com.g2452.demo.contract.Icontract;
import com.g2452.demo.base.BaseModel;
import com.g2452.demo.mvp.view.HomeView;
import com.g2452.demo.util.LogUtil;

import java.util.Map;

/**
 * 作者：G on 2020/4/21
 * 时间：17:12
 * 概述：
 */
public class HomeModel extends BaseModel {

    private HomeView mHomeView;

    public void getHomeModel(Context context,String url, Icontract.View view ,Map<String,String> map) {
        getString(context,url, map,true,true);
        mHomeView = ((HomeView) view);
    }


    @Override
    public void successString(String data) {
        ((HomeView) mHomeView).getData(data);
    }

    @Override
    public void failString(String msg) {
        super.failString(msg);
        LogUtil.getInstance().d("网络失败");

    }
}
