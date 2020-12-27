package com.example.day12_24;

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
import com.example.day12_24.bean.SmartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryAdapter extends DelegateAdapter.Adapter {
    private ArrayList<SmartBean.DataBean.CategoryListBean.GoodsListBean> listBeans;
    private GridLayoutHelper gridLayoutHelper;
    private Context context;

    public CategoryAdapter(ArrayList<SmartBean.DataBean.CategoryListBean.GoodsListBean> listBeans, GridLayoutHelper gridLayoutHelper, Context context) {
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
        return new ViewHolder_Hot(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder_Hot holder1 = (ViewHolder_Hot) holder;
        SmartBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = listBeans.get(position);
        Glide.with(context).load(goodsListBean.getList_pic_url()).into(holder1.ivListPicUrl);
        holder1.tvName.setText(goodsListBean.getName());
        holder1.tvPrice.setText("¥"+goodsListBean.getRetail_price());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static
    class ViewHolder_Hot extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_list_pic_url)
        ImageView ivListPicUrl;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder_Hot(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
