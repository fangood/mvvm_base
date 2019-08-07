package com.zj.th.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.zj.databinding.mvvm.utils.ThreadUtil;
import com.zj.th.base.viewmodel.Progress;
import com.zj.th.base.viewmodel.AppViewModel;
import com.zj.th.base.widget.ThProgressDialog;
import com.zj.th.base.widget.ToastManager;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

/**
 */
public class ProgressDelegate {

    private Context context;
    private ThProgressDialog mProgressDialog;

    public <T extends AppViewModel> void subscribeViewModelChanges(@NonNull AppCompatActivity activity, T viewModel) {
        context = activity.getApplicationContext();
        if (mProgressDialog == null) {
            mProgressDialog = new ThProgressDialog(activity);
        }
        if (viewModel == null) {
            return;
        }
        subscribeViewModelChanges((LifecycleOwner) activity, viewModel);
    }

    public <T extends AppViewModel> void subscribeViewModelChanges(@NonNull Fragment fragment, T viewModel) {
        context = fragment.getActivity().getApplicationContext();
        if (mProgressDialog == null) {
            mProgressDialog = new ThProgressDialog(fragment.getActivity());
        }
        if (viewModel == null) {
            return;
        }
        subscribeViewModelChanges((LifecycleOwner) fragment, viewModel);
    }

    private <T extends AppViewModel> void subscribeViewModelChanges(@NonNull LifecycleOwner owner, T viewModel) {
        viewModel.getMessageEvent().observe(owner, message -> showToast(message));
        viewModel.getProgressStartEvent().observe(owner, progress -> {
            if (progress != null) {
                showProgress(progress.getTag(), progress.getMessage());
            }
        });
        viewModel.getProgressDoneEvent().observe(owner, tag -> dismissProgress(tag));
    }

    public void showToast(String message) {
        ToastManager.show(context, message);
    }

    public void showToast(String message, int duration) {
        ToastManager.show(context, message, duration);
    }

    private Map<String, String> mProgressStore = new HashMap<>();

    public void showProgress() {
        showProgress(Progress.DEFAULT_TAG, "");
    }

    public void showProgress(String message) {
        showProgress(Progress.DEFAULT_TAG, message);
    }

    public void showProgress(String tag, String message) {
        if (ThreadUtil.isMainThread()) {
            showProgressInternal(tag, message);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> showProgressInternal(tag, message));
        }
    }

    private void showProgressInternal(String tag, String message) {
        mProgressStore.put(tag, message);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    public void dismissProgress() {
        dismissProgress(Progress.DEFAULT_TAG);
    }

    public void dismissProgress(String tag) {
        if (ThreadUtil.isMainThread()) {
            dismissProgressInternal(tag);
        } else {
            new Handler(Looper.getMainLooper()).post(() -> dismissProgressInternal(tag));
        }
    }

    private void dismissProgressInternal(String tag) {
        mProgressStore.remove(tag);
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            if (mProgressStore.size() == 0) {
                mProgressDialog.dismiss();
            } else {
                mProgressDialog.setMessage(mProgressStore.entrySet().iterator().next().getValue());
            }
        }
    }

}
