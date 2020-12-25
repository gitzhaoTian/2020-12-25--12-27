package com.example.day12_24.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.day12_24.R;
import com.example.day12_24.adapter.MainLinearAdapter;
import com.example.day12_24.bean.Bann;
import com.example.day12_24.contract.MainContract;
import com.example.day12_24.presenter.HomePresenterImpl;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements MainContract.IHomeView {

    private RecyclerView rv_home;
    private DelegateAdapter adapter;
    private LinearLayoutHelper linearLayoutHelper;
    private ArrayList<Bann.DataBean.BannerBean> list;
    private MainLinearAdapter mainLinearAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        rv_home = view.findViewById(R.id.rv_home);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        rv_home.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        rv_home.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0,10);
//        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
//        // 公共属性
//        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
//        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
//        singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
//        singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
//        MainSingleAdapter mainSingleAdapter = new MainSingleAdapter(singleLayoutHelper);
        linearLayoutHelper = new LinearLayoutHelper(5);
        HomePresenterImpl presenter = new HomePresenterImpl(this);
        presenter.getBanner();
        list = new ArrayList<>();
        mainLinearAdapter = new MainLinearAdapter(linearLayoutHelper, list,getContext());
        adapter = new DelegateAdapter(virtualLayoutManager, true);
        rv_home.setAdapter(adapter);
    }

    @Override
    public void getBanner(Bann bann) {
        list.addAll(bann.getData().getBanner());
        mainLinearAdapter.notifyDataSetChanged();
        adapter.addAdapter(mainLinearAdapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}