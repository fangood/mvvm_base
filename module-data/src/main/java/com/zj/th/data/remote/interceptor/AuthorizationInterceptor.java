package com.zj.th.data.remote.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 在header中统一添加token
 *
 */
public class AuthorizationInterceptor implements Interceptor {

    protected final int HTTP_STATUS_CODE_UNAUTHORIZED = 401;

    protected final String HEADER_AUTHORIZATION_KEY = "Authorization";
    protected final String HEADER_AUTHORIZATION_VALUE_PREFIX = "Bearer ";

    private TokenExpiredCallback mTokenExpiredCallback;
    private TokenProvider mTokenProvider;

    public AuthorizationInterceptor(@NonNull TokenProvider provider, @NonNull TokenExpiredCallback callback) {
        mTokenProvider = provider;
        mTokenExpiredCallback = callback;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Headers originHeaders = request.headers();

        Headers newHeaders = new Headers.Builder()
                .add(HEADER_AUTHORIZATION_KEY, HEADER_AUTHORIZATION_VALUE_PREFIX + mTokenProvider.getToken())
                .build();

        request = request.newBuilder()
                .headers(originHeaders)
                .headers(newHeaders)
                .build();

        Response response = chain.proceed(request);

        if (response.code() == HTTP_STATUS_CODE_UNAUTHORIZED) {
            mTokenExpiredCallback.onTokenExpired();
        }

        return response;
    }
}
