package com.example.day12_24.utils;

public interface INetCallBack<T> {
    public void onSuccess(T t);
    public void onFail(String error);
}
