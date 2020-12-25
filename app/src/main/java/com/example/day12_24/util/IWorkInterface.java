package com.example.day12_24.util;

public interface IWorkInterface {
    public <T> void getBanner(String url,IHomeCallBack<T> callBack);
}
