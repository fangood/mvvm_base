package com.zj.th.data.remote.interceptor;


public interface TokenExpiredCallback {
    /**
     * token失效
     */
    void onTokenExpired();
}