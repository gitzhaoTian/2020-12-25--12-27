package com.example.day12_24.contract;

import com.example.day12_24.bean.Bann;
import com.example.day12_24.util.IHomeCallBack;

public class MainContract {
    public interface IHomeModel{
        <T> void getBanner(String uel, IHomeCallBack<T> callBack);
    }
    public interface IHomePresenter{
        void getBanner();
    }
    public interface IHomeView{
        void getBanner(Bann bann);
        void onFail(String error);
    }
}
