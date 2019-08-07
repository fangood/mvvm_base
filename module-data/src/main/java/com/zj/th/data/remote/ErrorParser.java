package com.zj.th.data.remote;

import com.google.gson.JsonSyntaxException;
import com.zj.th.data.remote.exception.ApiDataNullException;
import com.zj.th.data.remote.exception.ApiException;
import com.zj.th.data.remote.exception.ApiNotSuccessException;
import com.zj.th.data.remote.response.ApiResult;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;


public class ErrorParser {

    public static boolean parse(Throwable throwable, StringBuilder errorMsg) {
        if (throwable instanceof JsonSyntaxException) {
            errorMsg.append("服务器返回数据格式有误");
            return true;
        } else if (throwable instanceof SocketTimeoutException) {
            errorMsg.append("网络连接超时");
            return true;
        } else if (throwable instanceof IOException) {
            errorMsg.append("您的网络不给力，请检测网络后重试");
            return true;
        } else if (throwable instanceof UnknownHostException) {
            errorMsg.append("服务器异常");
            return true;
        } else if (throwable instanceof HttpException) {
            String message = null;
            switch (((HttpException) throwable).code()) {
                case 401:
                    message = "登录超时";
                    break;
                default:
                    message = "服务暂不可用";
                    break;
            }
            errorMsg.append(message);
            return true;
        } else if (throwable instanceof ApiNotSuccessException) {
            errorMsg.append(throwable.getMessage() == null ? "查询失败" : throwable.getMessage());
            return true;
        } else if (throwable instanceof ApiDataNullException) {
            errorMsg.append(throwable.getMessage() == null ? "数据为空" : throwable.getMessage());
            return true;
        } else if (throwable instanceof ApiException) {
            errorMsg.append(throwable.getMessage() == null ? "发生错误" : throwable.getMessage());
            return true;
        }
        errorMsg.append("未知错误");
        throwable.printStackTrace();
        return false;
    }

    public static String parse(Throwable throwable) {
        StringBuilder errorMsg = new StringBuilder();
        parse(throwable, errorMsg);
        return errorMsg.toString();
    }

    public static <T extends ApiResult> T mockResult(Class<T> resultType, Throwable throwable) {
        try {
            T result = resultType.newInstance();
            result.setCode("-1");
            result.setResult(false);
            result.setMessage(parse(throwable));
            return result;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return mockResult(throwable);
    }

    public static <T extends ApiResult> T mockResult(Throwable throwable) {
        return (T) new ApiResult(false, "-1", parse(throwable), null);
    }

}
