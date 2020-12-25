package com.example.day12_24.util;

public interface IHomeCallBack<T> {
    public void onSuccess(T t);
    public void onFail(String error);
}
