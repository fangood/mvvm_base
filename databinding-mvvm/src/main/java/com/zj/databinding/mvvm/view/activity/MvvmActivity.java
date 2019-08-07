package com.zj.databinding.mvvm.view.activity;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.zj.databinding.mvvm.view.ViewOwner;
import com.zj.databinding.mvvm.view.ViewOwnerHelper;


public abstract class MvvmActivity<VDB extends ViewDataBinding> extends BaseActivity implements ViewOwner<VDB> {

    private VDB mViewDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewOwnerHelper.onActivityCreate(this, this);
    }


    @Override
    public void onBindingCreated(@NonNull VDB binding) {
        mViewDataBinding = binding;
        ViewOwnerHelper.onBind(this);
    }

    @Override
    protected void onDestroy() {
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
