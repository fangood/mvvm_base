package com.zj.databinding.mvvm.view.adapter;

import androidx.databinding.ViewDataBinding;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.databinding.mvvm.view.ViewOwner;
import com.zj.databinding.mvvm.view.ViewOwnerHelper;

/**
 */
public abstract class MvvmListAdapter<T> extends BaseListAdapter<T> {

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemViewOwner itemViewOwner;

        if (convertView == null) {
            itemViewOwner = onCreateViewOwner(parent, getItem(position), position);
            ViewOwnerHelper.onCreateView(LayoutInflater.from(parent.getContext()), itemViewOwner, parent, false);
        } else {
            itemViewOwner = (ItemViewOwner) convertView.getTag();
        }

        ViewOwnerHelper.onBind(itemViewOwner);

        return itemViewOwner.getBinding().getRoot();
    }

    /**
     * 创建ViewOwner
     *
     * @param parent
     * @param item
     * @param position
     * @return
     */
    protected abstract ItemViewOwner onCreateViewOwner(ViewGroup parent, T item, int position);

    public abstract class ItemViewOwner<VDB extends ViewDataBinding> implements ViewOwner<VDB> {

        private VDB mViewDataBinding;

        @Override
        public void bindViewModels(@NonNull VDB binding) {
        }

        @Override
        public void onBindingCreated(@NonNull VDB binding) {
            mViewDataBinding = binding;
        }

        @Override
        public VDB getBinding() {
            return mViewDataBinding;
        }

    }
}
