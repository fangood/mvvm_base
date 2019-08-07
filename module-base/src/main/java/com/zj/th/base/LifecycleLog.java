package com.zj.th.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


public class LifecycleLog implements Application.ActivityLifecycleCallbacks {
    private int activityStartCount = 0;  //判断Activity 是否在前台，通过监听application 中的生命周期
    private OnAppBackListener onAppStatusListener;

    public LifecycleLog(OnAppBackListener onAppStatusListener) {
        this.onAppStatusListener = onAppStatusListener;
    }

    @Override
    public void onActivityResumed(Activity activity) {
//        Lg.d ("onActivityResumed---"+activity.getClass().getName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        Lg.d ("onActivityStopped---"+activity.getClass().getName());
        activityStartCount--;
        //数值从1到0说明是从前台切到后台
        if (activityStartCount == 0) {
            //从前台切到后台
            if (onAppStatusListener != null) {
                onAppStatusListener.onBack();
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
//        Lg.d ("onActivityStarted---"+activity.getClass());
        activityStartCount++;
        //数值从0变到1说明是从后台切到前台
        if (activityStartCount == 1) {
            //从后台切到前台
            if (onAppStatusListener != null) {
                onAppStatusListener.onFront();
            }
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    public interface OnAppBackListener {
        void onFront();

        void onBack();
    }

}
