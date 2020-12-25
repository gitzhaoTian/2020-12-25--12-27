package com.example.day12_24;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;

public class HomeFragment extends Fragment {

    private RecyclerView rv_home;

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
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper(5);
        DelegateAdapter adapter = new DelegateAdapter(virtualLayoutManager, true);

    }
}