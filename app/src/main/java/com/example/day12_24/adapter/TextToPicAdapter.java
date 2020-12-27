package com.example.day12_24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.example.day12_24.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextToPicAdapter extends DelegateAdapter.Adapter {
    private Context context;
    private LinearLayoutHelper linearLayoutHelper;
    private String title;

    public TextToPicAdapter(Context context, LinearLayoutHelper linearLayoutHelper, String title) {
        this.context = context;
        this.linearLayoutHelper = linearLayoutHelper;
        this.title = title;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return linearLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_text, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tvTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
