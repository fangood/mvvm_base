package com.zj.databinding.mvvm.view.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public abstract class BaseRecylerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private List<T> mData;

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void add(T item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
    }

    public void addAll(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
