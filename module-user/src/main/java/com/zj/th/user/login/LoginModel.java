package com.zj.th.user.login;

import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zj.th.base.App;
import com.zj.th.base.TokenManager;
import com.zj.th.data.CityManager;
import com.zj.th.data.UserInfoManager;
import com.zj.th.data.local.pojo.UserInfo;
import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.request.LoginByPasswordRequest;
import com.zj.th.data.remote.request.LoginBySmsCodeRequest;
import com.zj.th.data.remote.response.ApiResult;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginModel {

    /**用户密码登陆*/
    public Observable<UserInfo> loginByPassword(String phone, String password) {
        return login(ThApi.getBaseService().loginByName(new LoginByPasswordRequest(phone, password)));
    }

    /**验证码登陆*/
    public Observable<UserInfo> loginBySmsCode(String phone, String smsCode) {
        return login(ThApi.getBaseService().loginBySmsCode(new LoginBySmsCodeRequest(phone, smsCode)));
    }

    protected Observable<UserInfo> login(Observable<ApiResult<String>> observable) {
        // 获取包含登录接口的Service
        return observable
                // 在io线程中调用接口
                .subscribeOn(Schedulers.io())
                // 在io线程中执行回调
                .observeOn(AndroidSchedulers.mainThread())
                // 验证接口调用是否成功
                .doOnNext(result -> result.assertSuccess())
                // 验证接口返回token是否为空
                .doOnNext(result -> result.assertDataNonNull())
                // 保存获取到的token
                .doOnNext(result -> saveToken(result.getData()))
                // 加载城市列表
                .doOnNext(result -> loadCities())
                /**获得登录用户的权限信息，配置信息*/
                .flatMap(result -> UserInfoManager.get().loadUserInfo())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(result -> App.get().setLoginUser(UserInfoManager.get().getOriginPermission()))
                .doOnNext(result -> loginMain());
    }

    private void loginMain() {
        ARouter.getInstance()
                .build("/app/main")
                .withFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .navigation();
    }


    protected void saveToken(String token) {
        Observable.just(token)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(t -> {
                    // 保存token的逻辑
                    TokenManager.get().setToken(t);
                });
    }

    protected void loadCities() {
        CityManager.get().loadCities();
    }

    public void getToken() {
    }
}
