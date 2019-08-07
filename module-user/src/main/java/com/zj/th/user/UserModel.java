package com.zj.th.user;

import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.response.ApiResult;
import com.zj.th.data.remote.user.UserDetailInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserModel {


    // 获取个人详细信息
    public Observable<ApiResult<UserDetailInfo>> getUserDetailInfo(int userId) {
        return ThApi.getAuthService()
                .getUserDetailInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
