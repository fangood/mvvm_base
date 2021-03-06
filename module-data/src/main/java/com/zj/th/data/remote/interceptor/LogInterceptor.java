package com.zj.th.data.remote.interceptor;

import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okio.Buffer;


public class LogInterceptor implements Interceptor {

    private boolean debug = true;

    private String filePath;
    private String fileName;

    private Gson gson;

    public LogInterceptor(boolean debug) {

        this.debug = debug;

        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com/zj/log/";
        fileName = "api_log.txt";

        File file = new File(filePath, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e("LogInterceptor", "日志文件创建失败");
            }
        }

        gson = new GsonBuilder().setPrettyPrinting()
                .serializeNulls()
                .create();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(request.method() + " " + request.url() + "\n");
        sb.append("-----------------= Request =-----------------\n");
        sb.append(request.headers() + "\n");
        sb.append(prettyJson(bodyToString(request.body())) + "\n");
        Response response = chain.proceed(request);

        sb.append("-----------------= Response =-----------------\n");
        String bodyString = null;

        if (response.body().contentType() != null
                && response.body().contentType().toString().contains("json")) {
            bodyString = response.body().string();
            sb.append(prettyJson(bodyString) + "\n");
        } else {
            sb.append("not json data");
        }

        sb.append("\n");
        Log.d("LogInterceptor",sb.toString());

        if (debug) {
            Platform.get().log(Platform.INFO, sb.toString(), null);
        }

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString == null ? response.body().bytes() : bodyString.getBytes()))
                .build();
    }

    private String bodyToString(RequestBody body) throws IOException {
        if (body != null) {
            if (body instanceof MultipartBody) {
                return "MultipartBody";
            } else if (body instanceof FormBody) {
                return "FormBody";
            } else {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                return buffer.readUtf8();
            }
        }
        return "";
    }

    private String bodyToString(ResponseBody body) throws IOException {
        if (body != null && body.contentType().toString().contains("json")) {
            return body.string();
        }
        return "";
    }

    private String prettyJson(final String json) {
        try {
            return gson.toJson(new JsonParser().parse(json));
        } catch (Exception e) {
            return "not json data";
        }
    }
}
