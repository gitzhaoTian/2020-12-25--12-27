package com.example.day12_24.utils;

public interface INetWorkInterFace {
    <T> void get(String url , INetCallBack<T> callBack);
}
