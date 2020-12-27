package com.example.day12_24.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day12_24.R;
import com.example.day12_24.bean.SmartBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotGoodsAdapter extends DelegateAdapter.Adapter {
    private LinearLayoutHelper linearLayoutHelper;
    private ArrayList<SmartBean.DataBean.HotGoodsListBean> listBeans;
    private Context context;

    public HotGoodsAdapter(LinearLayoutHelper linearLayoutHelper, ArrayList<SmartBean.DataBean.HotGoodsListBean> listBeans, Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotgoods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        SmartBean.DataBean.HotGoodsListBean hotGoodsListBean = listBeans.get(position);
        Glide.with(context).load(hotGoodsListBean.getList_pic_url()).into(holder1.ivListPicUrl);
        holder1.tvTitle.setText(Html.fromHtml("<h2>"+hotGoodsListBean.getName()+"</h2>"
        +"<h7>"+hotGoodsListBean.getGoods_brief()+"</h7>"));
        holder1.tvPrice.setText("¥ "+hotGoodsListBean.getRetail_price());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_list_pic_url)
        ImageView ivListPicUrl;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
