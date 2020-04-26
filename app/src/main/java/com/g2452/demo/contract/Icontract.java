package com.g2452.demo.contract;

import android.content.Context;

import java.util.Map;

/*
 * mvp契约类
 * */

public interface Icontract {

    interface Model {

        void getString(Context context, String url, Map<String, String> map, boolean isShow, boolean isUpdt);

        void postString(Context context, String url, Map<String, String> map, boolean isShow, boolean isUpdt);

        void stopRequest();

    }

    interface View {

    }

    interface Persenter<M extends Model, V extends View> {

        //        注册view
        void registerView();

        //注册model
        void registerModel();

        //      获取view
        V getView();

        //销毁perstnter
        void destory();

    }

}
