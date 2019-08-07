package com.zj.databinding.mvvm.view.adapter;

import androidx.databinding.ObservableList;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.databinding.mvvm.view.ViewOwner;
import com.zj.databinding.mvvm.view.ViewOwnerHelper;

import java.lang.ref.WeakReference;
import java.util.List;

public abstract class MvvmRecyclerAdapter<T> extends RecyclerView.Adapter<MvvmRecyclerAdapter.ViewHolder> {

    private WeakReferenceOnListChangedCallback<T> callback;
    private List<T> items;

    @NonNull
    @Override
    public MvvmRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewOwner owner = onCreateViewOwner(parent, viewType);
        ViewOwnerHelper.onCreateView(LayoutInflater.from(parent.getContext()), owner, parent, false);
        return new MvvmRecyclerAdapter.ViewHolder(owner);
    }

    /**
     * 根据viewType创建相应的ViewOwner
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract ViewOwner onCreateViewOwner(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull MvvmRecyclerAdapter.ViewHolder holder, int position) {
        ViewOwnerHelper.onBind(holder.getViewOwner());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(@Nullable List<T> items) {
        if (this.items == items) {
            return;
        }
        // If a recyclerview is listening, set up listeners. Otherwise wait until one is attached.
        // No need to make a sound if nobody is listening right?
        if (this.items instanceof ObservableList) {
            ((ObservableList<T>) this.items).removeOnListChangedCallback(callback);
            callback = null;
        }
        if (items instanceof ObservableList) {
            callback = new WeakReferenceOnListChangedCallback<>(this, (ObservableList<T>) items);
            ((ObservableList<T>) items).addOnListChangedCallback(callback);
        }
        this.items = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ViewOwner mViewOwner;

        private ViewHolder(View itemView) {
            super(itemView);
        }

        public ViewHolder(ViewOwner owner) {
            this(owner.getBinding().getRoot());
            this.mViewOwner = owner;
        }

        public ViewOwner getViewOwner() {
            return mViewOwner;
        }
    }

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

        final WeakReference<MvvmRecyclerAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(MvvmRecyclerAdapter<T> adapter, ObservableList<T> items) {
            this.adapterRef = AdapterReferenceCollector.createRef(adapter, items, this);
        }

        @MainThread
        @Override
        public void onChanged(ObservableList sender) {
            MvvmRecyclerAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyDataSetChanged();
        }

        @MainThread
        @Override
        public void onItemRangeChanged(ObservableList sender, final int positionStart, final int itemCount) {
            MvvmRecyclerAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @MainThread
        @Override
        public void onItemRangeInserted(ObservableList sender, final int positionStart, final int itemCount) {
            MvvmRecyclerAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @MainThread
        @Override
        public void onItemRangeMoved(ObservableList sender, final int fromPosition, final int toPosition, final int itemCount) {
            MvvmRecyclerAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            for (int i = 0; i < itemCount; i++) {
                adapter.notifyItemMoved(fromPosition + i, toPosition + i);
            }
        }

        @MainThread
        @Override
        public void onItemRangeRemoved(ObservableList sender, final int positionStart, final int itemCount) {
            MvvmRecyclerAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            adapter.notifyItemRangeRemoved(positionStart, itemCount);
        }
    }
}
