package com.g2452.demo.http;

import android.app.Activity;
import android.content.Context;

import com.g2452.demo.APP;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class HttpUtlis {


    private static HttpUtlis httpUtlis = null;
    private static BaseService mBaseService;
    private String data;
    private Context context= APP.getContext();

    public HttpUtlis() { }

    /**
     * 构建单利模式
     */

    public static HttpUtlis getInstance() {
        if (httpUtlis == null) {
            httpUtlis = new HttpUtlis();
        }
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(HttpUrl.BASE_URL).build();
        mBaseService = retrofit.create(BaseService.class);
        return httpUtlis;
    }

    /**
     * get方法
    * */

    public HttpUtlis get(String url , Map<String,String> map,boolean isShow){
        doHttp("get",url,map,isShow);
        return this;
    }
    /**
     * post方法
    * */
    public HttpUtlis post(String url , Map<String,String> map,Boolean isShow){
        doHttp("post",url,map,isShow);
        return this;
    }

    private void doHttp(String httpType, String url, Map<String, String> map, Boolean isShow) {
        Observable<ResponseBody> ob = null;
        if (httpType == "post") {
            ob = mBaseService.post(url, map);
        }else if(httpType=="get"){
            ob = mBaseService.get(url, map);
        }
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private BaseObsservabler observer = new BaseObsservabler<ResponseBody>() {

        @Override
        public void onNext(ResponseBody responseBody) {
//            if (loadingDialog != null) {
//                loadingDialog.dismiss();
//            }
//            String data = null;
            try {
                data = responseBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            listener.Success(data);
//            if (isreadCache && !SqlUtil.getInstens().queryIsExist(type+"")) {
//                SqlUtil.getInstens().insert(type+"", data);
//            } else {
//                if (updateCache) {
//                    SqlUtil.getInstens().upDataByType(type+"", data);
//                }
//            }
        }

        @Override
        public void onError(Throwable e) {
            listener.Error(e.getMessage());
//            if (loadingDialog != null) {
//                loadingDialog.dismiss();
//            }
        }
    };

    public interface HttpListener {
        void Success(String data);
        void Error(String msg);
    }

    private HttpListener listener;

    public HttpUtlis result(HttpListener listener) {
        this.listener = listener;
        return this;
    }


}
