package com.zj.databinding.mvvm.view;

import androidx.lifecycle.LifecycleObserver;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class BaseLifecycleRegistry {

    private static BaseLifecycleRegistry mInstance;

    protected List<LifecycleObserver> mActivityLifecycleObservers;
    protected List<LifecycleObserver> mFragmentLifecycleObservers;

    public static BaseLifecycleRegistry get() {
        if (mInstance == null) {
            mInstance = new BaseLifecycleRegistry();
        }
        return mInstance;
    }

    private BaseLifecycleRegistry() {
        mActivityLifecycleObservers = new ArrayList<>();
        mFragmentLifecycleObservers = new ArrayList<>();
    }

    public static void addActivityLifecycleObserver(@NonNull LifecycleObserver observer) {
        get().mActivityLifecycleObservers.add(observer);
    }

    public static void addFragmentLifecycleObserver(@NonNull LifecycleObserver observer) {
        get().mFragmentLifecycleObservers.add(observer);
    }

    public List<LifecycleObserver> getActivityLifecycleObservers() {
        return mActivityLifecycleObservers;
    }

    public List<LifecycleObserver> getFragmentLifecycleObservers() {
        return mFragmentLifecycleObservers;
    }
}
