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
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day12_24.R;
import com.example.day12_24.bean.SmartBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SmartAdapter extends DelegateAdapter.Adapter {
    private GridLayoutHelper gridLayoutHelper;
    private ArrayList<SmartBean.DataBean.BrandListBean> list;
    private Context context;

    public SmartAdapter(GridLayoutHelper gridLayoutHelper, ArrayList<SmartBean.DataBean.BrandListBean> list, Context context) {
        this.gridLayoutHelper = gridLayoutHelper;
        this.list = list;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return gridLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_smart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        SmartBean.DataBean.BrandListBean listBean = list.get(position);
        Glide.with(context).load(listBean.getNew_pic_url()).into(holder1.ivNewPicUrl);
        holder1.tvTitle.setText(Html.fromHtml("<h2>"+listBean.getName()+"</h2>"
        +"<h7>"+listBean.getFloor_price()+"元起"+"</h7>"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_new_pic_url)
        ImageView ivNewPicUrl;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
