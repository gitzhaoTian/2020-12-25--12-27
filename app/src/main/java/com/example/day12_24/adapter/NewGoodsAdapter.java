package com.example.day12_24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day12_24.R;
import com.example.day12_24.bean.SmartBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewGoodsAdapter extends DelegateAdapter.Adapter {
    private ArrayList<SmartBean.DataBean.NewGoodsListBean> listBeans;
    private GridLayoutHelper gridLayoutHelper;
    private Context context;

    public NewGoodsAdapter(ArrayList<SmartBean.DataBean.NewGoodsListBean> listBeans, GridLayoutHelper gridLayoutHelper, Context context) {
        this.listBeans = listBeans;
        this.gridLayoutHelper = gridLayoutHelper;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_newgoods, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        SmartBean.DataBean.NewGoodsListBean listBean = listBeans.get(position);
        Glide.with(context).load(listBean.getList_pic_url()).into(holder1.ivListPicUrl);
        holder1.tvName.setText(listBean.getName());
        holder1.tvPrice.setText("¥"+listBean.getRetail_price());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_list_pic_url)
        ImageView ivListPicUrl;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
