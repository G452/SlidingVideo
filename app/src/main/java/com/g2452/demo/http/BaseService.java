package com.g2452.demo.http;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
/**
 * 作者：G
 * 时间：2020/4/22  18:23
 * 概述： * BaseService
 */
public interface BaseService {

    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<ResponseBody> post(@Url String url, @FieldMap Map<String,String> map);


}
