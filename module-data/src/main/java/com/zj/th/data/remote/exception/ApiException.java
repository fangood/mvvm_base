package com.zj.th.data.remote.exception;

import androidx.annotation.NonNull;
import android.text.TextUtils;

import com.zj.th.data.remote.response.ApiJavaResult;
import com.zj.th.data.remote.response.ApiResult;
import com.zj.th.data.remote.response.ErrorCode;

public class ApiException extends Exception {

    private ApiResult mApiResult;
    private ApiJavaResult mApiJavaResult;

    public ApiException(@NonNull ApiResult result) {
        super(TextUtils.isEmpty(result.getMessage()) ? ErrorCode.getErrorMsg(result.getCode()) : result.getMessage());
        mApiResult = result;
    }

    public ApiException(@NonNull ApiJavaResult result) {
        super(TextUtils.isEmpty(result.getMessage()) ? String.valueOf(result.getCode()) : result.getMessage());
        mApiJavaResult = result;
    }

    public String getCode() {
        return getApiResult().getCode();
    }

    public String getApiMessage() {
        return getApiResult().getMessage();
    }

    public <T> T getApiData() {
        return (T) getApiResult().getData();
    }

    public <T> ApiResult<T> getApiResult() {
        return mApiResult;
    }

    ///////////  java   //////////////////
    public int getJavaCode() {
        return getmApiJavaResult().getCode();
    }

    public String getApiJavaMessage() {
        return getmApiJavaResult().getMessage();
    }

    public <T> T getApiJavaData() {
        return (T) getmApiJavaResult().getData();
    }

    public ApiJavaResult getmApiJavaResult() {
        return mApiJavaResult;
    }
}
