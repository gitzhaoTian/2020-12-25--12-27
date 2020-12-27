package com.example.day12_24.contract;


import com.example.day12_24.bean.BanBean;
import com.example.day12_24.utils.INetCallBack;

public class GirlsContract {
    public interface GirlsModel{
        <T> void getGirls(String url , INetCallBack<T> callBack);
    }
    public interface GirlsPresenter{
        void get();
    }
    public interface GirlsView{
        void getGirls(BanBean girlsBean);
        void onFail(String error);
    }
}