package com.zj.databinding.mvvm.view.fragment;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zj.databinding.mvvm.view.ViewOwner;
import com.zj.databinding.mvvm.view.ViewOwnerHelper;


public abstract class MvvmFragment<VDB extends ViewDataBinding> extends BaseFragment implements ViewOwner<VDB> {

    private VDB mViewDataBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewOwnerHelper.onFragmentCreateView(inflater, this, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onBindingCreated(@NonNull VDB binding) {
        mViewDataBinding = binding;
        ViewOwnerHelper.onBind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ViewOwnerHelper.onViewDestroy(this);
    }

    public VDB getViewDataBinding() {
        return getBinding();
    }

    @Override
    public VDB getBinding() {
        return mViewDataBinding;
    }
}
