package com.example.day12_24.presenter;


import com.example.day12_24.base.BasePresenter;
import com.example.day12_24.bean.BanBean;
import com.example.day12_24.contract.GirlsContract;
import com.example.day12_24.model.GirlsModelImpl;
import com.example.day12_24.utils.INetCallBack;

public class GirlsPresenterImpl extends BasePresenter implements GirlsContract.GirlsPresenter {
    private final GirlsModelImpl model;
    private GirlsContract.GirlsView girlsView;

    public GirlsPresenterImpl(GirlsContract.GirlsView girlsView) {
        this.girlsView = girlsView;
        model = new GirlsModelImpl(this);
    }

    @Override
    public void get() {
        model.getGirls("index", new INetCallBack<BanBean>() {
            @Override
            public void onSuccess(BanBean girlsBean) {
                girlsView.getGirls(girlsBean);
            }

            @Override
            public void onFail(String error) {
                girlsView.onFail(error);
            }
        });
    }
}
