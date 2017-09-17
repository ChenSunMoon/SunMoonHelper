package com.sunmoon.helper.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xinghongfei on 16/8/12.
 */
public class ApiManage {


    private static ApiManage apiManage;
    private Object monitor = new Object();
    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }
    public TuLingApi tuLingApi;
    public TuLingApi getTuLing(){
        if(tuLingApi == null) {
            synchronized (monitor) {
                if (tuLingApi == null){
                    tuLingApi=new Retrofit.Builder()
                            .baseUrl("http://www.tuling123.com/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(TuLingApi.class);
                }
            }
        }
        return tuLingApi;
    }

}
