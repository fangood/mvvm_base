package com.zj.th.data.remote.response;

import com.zj.th.data.remote.exception.ApiDataNullException;
import com.zj.th.data.remote.exception.ApiNotSuccessException;

import java.io.Serializable;

/**
 * 接口返回数据的基本数据格式
 *
 * @param <T>
 */
public class ApiResult<T> implements Serializable {

    private boolean result;
    private String code;
    private String message;
    private T data;

    public ApiResult() {
    }

    public ApiResult(boolean result, String code, String message, T data) {
        this.result = result;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void assertSuccess() throws ApiNotSuccessException {
        if (!result) {
            throw new ApiNotSuccessException(this);
        }
    }

    public void assertDataNonNull() throws ApiDataNullException {
        if (data == null) {
            throw new ApiDataNullException(this);
        }
    }
}
