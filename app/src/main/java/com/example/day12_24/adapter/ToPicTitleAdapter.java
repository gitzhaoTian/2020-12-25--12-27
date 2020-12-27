package com.example.day12_24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day12_24.R;
import com.example.day12_24.bean.SmartBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToPicTitleAdapter extends RecyclerView.Adapter<ToPicTitleAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SmartBean.DataBean.TopicListBean> listBeans;

    public ToPicTitleAdapter(Context context, ArrayList<SmartBean.DataBean.TopicListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SmartBean.DataBean.TopicListBean topicListBean = listBeans.get(position);
        Glide.with(context).load(topicListBean.getItem_pic_url()).into(holder.ivItemPicUrl);
        holder.tvTitle.setText(topicListBean.getTitle());
        holder.tvPrice.setText("¥ "+topicListBean.getPrice_info()+"起");
        holder.tvSubtitle.setText(topicListBean.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_item_pic_url)
        ImageView ivItemPicUrl;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_subtitle)
        TextView tvSubtitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
