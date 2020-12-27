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
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.day12_24.R;
import com.example.day12_24.bean.BanBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainLinearAdapter extends DelegateAdapter.Adapter {

    private LinearLayoutHelper linearLayoutHelper;
    private ArrayList<Integer> list;
    private Context context;

    public MainLinearAdapter(LinearLayoutHelper linearLayoutHelper, ArrayList<Integer> list, Context context) {
        this.linearLayoutHelper = linearLayoutHelper;
        this.list = list;
        this.context = context;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_linear, parent, false);
            return new ViewHolder_Search(view);
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner, parent, false);
        return new ViewHolder_Banner(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType==0){
            ViewHolder_Search viewHolder = (ViewHolder_Search) holder;
            viewHolder.tvSearch.setText("商品搜索,共239款好物");
        }
        if (itemViewType==1){
            ViewHolder_Banner holder_banner = (ViewHolder_Banner) holder;
            holder_banner.banner.setImages(list)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    }).start();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    static
    class ViewHolder_Search extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_search)
        TextView tvSearch;

        ViewHolder_Search(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static
    class ViewHolder_Banner extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner banner;

        ViewHolder_Banner(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
