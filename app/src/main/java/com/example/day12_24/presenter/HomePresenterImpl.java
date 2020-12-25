package com.example.day12_24.presenter;

import com.example.day12_24.base.BasePresenter;
import com.example.day12_24.bean.Bann;
import com.example.day12_24.contract.MainContract;
import com.example.day12_24.model.HomeModelImpl;
import com.example.day12_24.util.IHomeCallBack;

public class HomePresenterImpl extends BasePresenter implements MainContract.IHomePresenter {
    private final HomeModelImpl model;
    private MainContract.IHomeView homeView;

    public HomePresenterImpl(MainContract.IHomeView homeView) {
        this.homeView = homeView;
        model = new HomeModelImpl(this);
    }

    @Override
    public void getBanner() {
        model.getBanner("index", new IHomeCallBack<Bann>() {
            @Override
            public void onSuccess(Bann bann) {
                homeView.getBanner(bann);
            }

            @Override
            public void onFail(String error) {
                homeView.onFail(error);
            }
        });
    }
}
