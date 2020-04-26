package com.g2452.demo.base;

import android.content.Context;
import android.widget.Toast;

import com.g2452.demo.contract.Icontract;
import com.g2452.demo.http.HttpUrl;
import com.g2452.demo.http.HttpUtlis;

import java.util.Map;

/**
 * 作者：G on 2020/4/21
 * 时间：17:00
 * 概述：
 */
public class BaseModel implements Icontract.Model {
    @Override
    public void getString(Context context, String url, Map<String, String> map, boolean isShow, boolean isUpdt) {

        HttpUtlis.getInstance().result(new HttpUtlis.HttpListener() {
            @Override
            public void Success(String data) {
                successString(data);
            }

            @Override
            public void Error(String msg) {
                failString(msg);
                Toast.makeText(context,"网络繁忙，请稍后再试！"+msg,Toast.LENGTH_SHORT).show();
            }
        }).get(url,map,isShow);

    }

    @Override
    public void postString(Context context, String url, Map<String, String> map, boolean isShow, boolean isUpdt) {
        HttpUtlis.getInstance().result(new HttpUtlis.HttpListener() {
            @Override
            public void Success(String data) {
                successString(data);
            }

            @Override
            public void Error(String msg) {
                failString(msg);
                Toast.makeText(context,"网络繁忙，请稍后再试！",Toast.LENGTH_SHORT).show();
            }
        }).post(url,map,isShow);
    }

    @Override
    public void stopRequest() {

    }

    //请求成功
    public void successString(String data) {

    }

    //请求失败
    public void failString(String msg) {

    }
}
