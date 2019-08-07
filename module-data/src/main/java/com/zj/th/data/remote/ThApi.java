package com.zj.th.data.remote;

import androidx.annotation.NonNull;

import com.zj.th.data.remote.interceptor.AuthorizationInterceptor;
import com.zj.th.data.remote.interceptor.LogInterceptor;
import com.zj.th.data.remote.interceptor.TokenExpiredCallback;
import com.zj.th.data.remote.interceptor.TokenProvider;
import com.zj.th.data.remote.service.AuthService;
import com.zj.th.data.remote.service.BaseService;
import com.zj.th.data.remote.service.DecorationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Interceptor;

/**
 *
 * 项目API接口服务
 *
 */
public class ThApi {

    private static HashMap<String, Object> mServiceStore;

    public static String deviceId;

    private ThApi() {
    }

    public static void init(boolean debug, String deviceId, TokenProvider provider, TokenExpiredCallback callback) {
        ThApi.deviceId = deviceId;
        mServiceStore = new HashMap<>(6);
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new AuthorizationInterceptor(provider, callback));
        interceptors.add(new LogInterceptor(debug));
        RetrofitClient.init(BuildConfig.BASE_URL, getAuthenticator(), interceptors);
    }

    public static AuthService getAuthService() {
        return getService(AuthService.class);
    }

    public static BaseService getBaseService() {
        return getService(BaseService.class);
    }

    public static DecorationService getDecorationService() { return getService(DecorationService.class); }

    /**
     * 获取clazz对应的Service实例
     *
     * @param clazz
     * @return Service实例
     */
    public static <T> T getService(@NonNull Class<? extends T> clazz) {
        String key = clazz.getCanonicalName();
        T service = (T) mServiceStore.get(key);
        if (service == null) {
            service = create(clazz);
            mServiceStore.put(key, service);
        }
        return service;
    }

    /**
     * 根据传入的Service接口，创建接口实例
     *
     * @param service
     * @return Service实例
     */
    protected static <T> T create(@NonNull Class<? extends T> service) {
        return RetrofitClient.client()
                .retrofit()
                .create(service);
    }

    protected static Authenticator getAuthenticator() {
        return (route, response) -> null;
    }

}
