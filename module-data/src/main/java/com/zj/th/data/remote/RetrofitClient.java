package com.zj.th.data.remote;

import com.zj.th.data.remote.converter.ApiConverterFactory;

import org.apache.http.conn.ssl.AllowAllHostnameVerifier;

import java.net.Proxy;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitClient {

    private static RetrofitClient mInstance;
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    private RetrofitClient() {
    }

    private RetrofitClient(String baseUrl, Authenticator authenticator, List<Interceptor> interceptors) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .authenticator(authenticator)
                .sslSocketFactory(getSSLSocketFactory(), getTrustManager())
                .hostnameVerifier(getHostnameVerifier())
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES);

        if (!BuildConfig.PROXY_ENABLE) {
            builder.proxy(Proxy.NO_PROXY);//是否可抓包
        }

        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }

        mOkHttpClient = builder.build();

        // 初始化Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ApiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
    }

    public static RetrofitClient init(String baseUrl, Authenticator authenticator, List<Interceptor> interceptors) {
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient(baseUrl, authenticator, interceptors);
                }
            }
        }
        return mInstance;
    }

    public static RetrofitClient client() {
        return mInstance;
    }

    public Retrofit retrofit() {
        return mRetrofit;
    }

    public OkHttpClient okHttpClient() {
        return mOkHttpClient;
    }

    protected SSLSocketFactory getSSLSocketFactory() {
        return new HttpsSocketFactoryBuilder()
                .trust(BuildConfig.SERVER_PUBLIC_KEY)
                // .client(getResources().openRawResource(R.raw.client), "password")
                .build();
    }

    protected static X509TrustManager getTrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws
                    CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws
                    CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    protected HostnameVerifier getHostnameVerifier() {
        return new AllowAllHostnameVerifier();
    }

}
