package com.example.day12_24.model;

import com.example.day12_24.contract.MainContract;
import com.example.day12_24.util.IHomeCallBack;
import com.example.day12_24.util.RetrofitUtils;


public class HomeModelImpl implements MainContract.IHomeModel {
    private MainContract.IHomePresenter presenter;

    public HomeModelImpl(MainContract.IHomePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void getBanner(String uel, IHomeCallBack<T> callBack) {
        RetrofitUtils.getRetrofitUtils().getBanner(uel,callBack);
    }
}
