package com.zj.databinding.mvvm.view.fragment;

import androidx.lifecycle.LifecycleObserver;
import androidx.fragment.app.Fragment;

import com.zj.databinding.mvvm.view.BaseLifecycleRegistry;

import java.util.List;


public class BaseFragment extends Fragment {

    public BaseFragment() {
        addLifecycleObservers();
    }

    protected void addLifecycleObservers() {
        List<LifecycleObserver> observers = BaseLifecycleRegistry.get().getFragmentLifecycleObservers();
        for (LifecycleObserver observer : observers) {
            if (observer != null) {
                getLifecycle().addObserver(observer);
            }
        }
    }
}
