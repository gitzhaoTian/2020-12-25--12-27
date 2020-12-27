package com.example.day12_24.api;

import com.example.day12_24.bean.BanBean;
import com.example.day12_24.bean.SmartBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET
    Observable<BanBean> getGirls(@Url String url);
    String BASEURL = "http://cdwan.cn/api/";
    @GET
    Observable<SmartBean> getSmart(@Url String url);
}
