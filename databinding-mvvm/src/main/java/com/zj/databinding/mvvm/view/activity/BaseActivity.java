package com.zj.databinding.mvvm.view.activity;

import androidx.lifecycle.LifecycleObserver;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.zj.databinding.mvvm.view.BaseLifecycleRegistry;

import java.util.List;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addLifecycleObservers();
        super.onCreate(savedInstanceState);
    }

    protected void addLifecycleObservers() {
        List<LifecycleObserver> observers = BaseLifecycleRegistry.get().getActivityLifecycleObservers();
        for (LifecycleObserver observer : observers) {
            if (observer != null) {
                getLifecycle().addObserver(observer);
            }
        }
    }
}
