package com.zj.th.data.remote.response;

import com.zj.th.data.remote.exception.ApiDataNullException;
import com.zj.th.data.remote.exception.ApiNotSuccessException;

import java.io.Serializable;

public class ApiJavaResult<T> implements Serializable {

    private int code;//200成功 否则失败
    private String message;//提示信息
    private T data;//服务器返回的数据
    private int totalPages;//极光消息页数
    private String access_token;//极光token

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void assertSuccess() throws ApiNotSuccessException {
        if (code != 200) {
            throw new ApiNotSuccessException(this);
        }
    }

    public void assertDataNonNull() throws ApiDataNullException {
        if (data == null) {
            throw new ApiDataNullException(this);
        }
    }
}
