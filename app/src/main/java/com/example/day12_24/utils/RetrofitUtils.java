package com.example.day12_24.utils;


import androidx.annotation.NonNull;

import com.example.day12_24.api.ApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitUtils implements INetWorkInterFace {
    private static RetrofitUtils retrofitUtils;
    private final ApiService apiService;

    private RetrofitUtils() {
        apiService = new Retrofit.Builder()
                .baseUrl(URLConstant.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

    public static RetrofitUtils getRetrofitUtils() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    @Override
    public <T> void get(String url, INetCallBack<T> callBack) {
        apiService.getGirls(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
//        new Observer<ResponseBody>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull ResponseBody responseBody) {
//                try {
//                    String string = responseBody.string();
//                    Type[] genericInterfaces = callBack.getClass().getGenericInterfaces();
//                    Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
//                    Type type = actualTypeArguments[0];
//                    T result = new Gson().fromJson(string, type);
//                    callBack.onSuccess(result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                callBack.onFail("网络异常:" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }
    }
}
