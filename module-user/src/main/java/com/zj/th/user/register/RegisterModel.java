package com.zj.th.user.register;

import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.request.CheckPhoneRequest;
import com.zj.th.data.remote.request.RegisterRequest;
import com.zj.th.data.remote.response.ApiResult;
import com.zj.th.data.remote.user.CheckSmsCodeAndPassword;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RegisterModel {

    /**
     * 注册
     * @param name
     * @param phone
     * @param password
     * @param smsCode
     * @param jobPositionNo
     * @return
     */
    public Observable<ApiResult> register(String name, String phone, String password, String smsCode, String jobPositionNo) {
        return ThApi.getBaseService()
                .register(new RegisterRequest(name, phone, password, smsCode, jobPositionNo))
                .subscribeOn(Schedulers.io())
                .doOnNext(ApiResult::assertSuccess);
    }


    /**
     * 验证手机号是否存在
     * */
    public Observable<ApiResult<String>> RegisterVerify(CheckPhoneRequest request){
        return ThApi.getBaseService().RegisterVerify(request)
                .subscribeOn(Schedulers.io())
                // 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<ApiResult> checkSmsCodeAndPassword(String phone, String smsCode,String password ,int type) {
        return ThApi.getBaseService()
                .checkSmsCodeAndPassword(new CheckSmsCodeAndPassword(phone, smsCode,password, type))
                .subscribeOn(Schedulers.io())
                .doOnNext(ApiResult::assertSuccess);
    }

}
