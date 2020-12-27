package com.example.day12_24.model;


import com.example.day12_24.contract.GirlsContract;
import com.example.day12_24.utils.INetCallBack;
import com.example.day12_24.utils.RetrofitUtils;

public class GirlsModelImpl implements GirlsContract.GirlsModel {
    private GirlsContract.GirlsPresenter presenter;

    public GirlsModelImpl(GirlsContract.GirlsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void getGirls(String url, INetCallBack<T> callBack) {
        RetrofitUtils.getRetrofitUtils().get(url,callBack);
    }
}
