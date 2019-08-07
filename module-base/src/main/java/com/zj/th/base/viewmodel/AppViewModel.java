package com.zj.th.base.viewmodel;

import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zj.databinding.mvvm.viewmodel.SingleLiveEvent;


public class AppViewModel extends ViewModel {

    private final SingleLiveEvent<String> mMessageEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Progress> mProgressStartEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<String> mProgressDoneEvent = new SingleLiveEvent<>();

    @MainThread
    protected void postMessage(String message) {
        if (isMainThread()) {
            mMessageEvent.setValue(message);
        } else {
            mMessageEvent.postValue(message);
        }
    }

    protected void postProgressStart() {
        postProgressStart(Progress.DEFAULT_TAG);
    }

    protected void postProgressStart(String tag) {
        postProgressStart(tag, "");
    }

    protected void postProgressStart(String tag, String message) {
        if (isMainThread()) {
            mProgressStartEvent.setValue(new Progress(tag, message));
        } else {
            mProgressStartEvent.postValue(new Progress(tag, message));
        }
    }

    protected void postProgressDone() {
        postProgressDone(Progress.DEFAULT_TAG);
    }

    protected void postProgressDone(String tag) {
        if (isMainThread()) {
            mProgressDoneEvent.setValue(tag);
        } else {
            mProgressDoneEvent.postValue(tag);
        }
    }

    public LiveData<String> getMessageEvent() {
        return mMessageEvent;
    }

    public LiveData<Progress> getProgressStartEvent() {
        return mProgressStartEvent;
    }

    public LiveData<String> getProgressDoneEvent() {
        return mProgressDoneEvent;
    }

    private boolean isMainThread() {
        return Looper.getMainLooper().equals(Looper.myLooper());
    }
}
