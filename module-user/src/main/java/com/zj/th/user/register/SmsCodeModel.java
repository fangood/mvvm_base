package com.zj.th.user.register;

import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.request.PostCodeRequest;
import com.zj.th.data.remote.response.ApiResult;
import com.zj.th.data.remote.user.CheckSmsCode;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 短信验证码
 */
public class SmsCodeModel {

    public Observable<ApiResult<String>> postCode(String phone, @PostCodeRequest.SmsCodeType int type) {
        return ThApi.getBaseService()
                .postCode(phone, type)
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> result.assertSuccess());
    }

    public Observable<ApiResult> checkSmsCode(String phone, String smsCode, int type) {
        return ThApi.getBaseService()
                .checkSmsCode(new CheckSmsCode(phone, smsCode, type))
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> result.assertSuccess());
    }

}
