package com.zj.th.data.remote.interceptor;

public interface TokenProvider {
    /**
     * 获取用户token
     *
     * @return
     */
    String getToken();
}