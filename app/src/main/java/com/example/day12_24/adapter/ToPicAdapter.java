package com.example.day12_24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.day12_24.R;
import com.example.day12_24.bean.SmartBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToPicAdapter extends DelegateAdapter.Adapter {
    private LinearLayoutHelper linearLayoutHelper;
    private ArrayList<SmartBean.DataBean.TopicListBean> listBeans;
    private Context context;

    public ToPicAdapter(LinearLayoutHelper linearLayoutHelper, ArrayList<SmartBean.DataBean.TopicListBean> listBeans, Context context) {
        this.linearLayoutHelper = linearLayoutHelper;
        this.listBeans = listBeans;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recy, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        holder1.rvTopic.setLayoutManager(linearLayoutManager);
        ToPicTitleAdapter toPicTitleAdapter = new ToPicTitleAdapter(context,listBeans);
        holder1.rvTopic.setAdapter(toPicTitleAdapter);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.rv_topic)
        RecyclerView rvTopic;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
