package com.zj.th.base.fragment;

import androidx.databinding.ViewDataBinding;

import com.zj.databinding.mvvm.view.fragment.MvvmFragment;
import com.zj.th.base.ProgressDelegate;
import com.zj.th.base.viewmodel.AppViewModel;


public abstract class AppMvvmFragment<VDB extends ViewDataBinding> extends MvvmFragment<VDB> {

    private ProgressDelegate mProgressDelegate = new ProgressDelegate();

    protected <T extends AppViewModel> void subscribeViewModelChanges(T viewModel) {
        mProgressDelegate.subscribeViewModelChanges(this, viewModel);
    }

    public void showToast(String message) {
        mProgressDelegate.showToast(message);
    }

    public void showToast(String message, int duration) {
        mProgressDelegate.showToast(message, duration);
    }

    public void showProgress() {
        mProgressDelegate.showProgress();
    }

    public void showProgress(String message) {
        mProgressDelegate.showProgress(message);
    }

    public void showProgress(String tag, String message) {
        mProgressDelegate.showProgress(tag, message);
    }

    public void dismissProgress() {
        mProgressDelegate.dismissProgress();
    }

    public void dismissProgress(String tag) {
        mProgressDelegate.dismissProgress(tag);
    }
}
