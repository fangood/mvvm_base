package com.zj.th.base;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.th.data.local.Authorization;
import com.zj.th.data.remote.interceptor.TokenExpiredCallback;
import com.zj.th.data.remote.interceptor.TokenProvider;

/**
 * token 管理
 *
 */
public class TokenManager implements TokenProvider, TokenExpiredCallback {

    private static TokenManager mInstance;
    private Context mApplicationContext;
    private String mToken;

    private boolean tokenExpiredHandlerEnabled = true;

    private TokenManager() {
        mApplicationContext = App.get();
        mToken = Authorization.get().getToken(getContext());
    }

    public static TokenManager get() {
        if (mInstance == null) {
            mInstance = new TokenManager();
        }
        return mInstance;
    }

    /**
     * 登录页打开时调用该方法
     */
    public void disableTokenExpiredHandler() {
        tokenExpiredHandlerEnabled = false;
    }

    /**
     * 登录完成后或登录页面关闭时调用该方法
     */
    public void enableTokenExpiredHandler() {
        tokenExpiredHandlerEnabled = true;
    }

    @Override
    public void onTokenExpired() {
        if (tokenExpiredHandlerEnabled) {
            setToken("");
            ARouter.getInstance()
                    .build("/user/Login")
                    .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    .navigation();
        }
    }

    @Override
    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
        Authorization.get().setToken(getContext(), token);
    }

    protected Context getContext() {
        return mApplicationContext;
    }
}
