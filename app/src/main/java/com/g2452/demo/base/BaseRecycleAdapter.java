package com.g2452.demo.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：G on 2020/4/22
 * 时间：17:13
 * 概述：
 */
public abstract class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    private List<T> list = new ArrayList<>();
    private Context mcontext;
    private int postion;

    public BaseRecycleAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void setList(List<T> datas) {
        this.list.clear();
        this.list.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(getLayoutId(), null);
        return new BaseViewHolder(view, mcontext);
    }

    protected abstract int getLayoutId();

    protected abstract void convert(BaseViewHolder holder, T t, int postion);

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        this.postion = position;
        convert(holder, list.get(position), postion);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
