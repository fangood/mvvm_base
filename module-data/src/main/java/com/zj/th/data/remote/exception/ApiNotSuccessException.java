package com.zj.th.data.remote.exception;

import androidx.annotation.NonNull;

import com.zj.th.data.remote.response.ApiJavaResult;
import com.zj.th.data.remote.response.ApiResult;


public class ApiNotSuccessException extends ApiException {

    public ApiNotSuccessException(@NonNull ApiResult result) {
        super(result);
    }

    public ApiNotSuccessException(@NonNull ApiJavaResult result) {
        super(result);
    }
}
