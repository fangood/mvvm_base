package com.zj.th.data.remote.exception;

import androidx.annotation.NonNull;

import com.zj.th.data.remote.response.ApiJavaResult;
import com.zj.th.data.remote.response.ApiResult;


public class ApiDataNullException extends ApiException {

    public ApiDataNullException(@NonNull ApiResult result) {
        super(result);
    }

    public ApiDataNullException(@NonNull ApiJavaResult result) {
        super(result);
    }
}
