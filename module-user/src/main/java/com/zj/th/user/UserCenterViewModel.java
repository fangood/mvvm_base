package com.zj.th.user;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.zj.th.base.viewmodel.BaseViewModel;
import com.zj.th.data.remote.ErrorParser;
import com.zj.th.data.remote.user.GetConfigsResponse;

public class UserCenterViewModel extends BaseViewModel {

    MutableLiveData<GetConfigsResponse> getConfigsLiveData = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public void getUserInfo(int userId) {
        new UserModel()
                .getUserDetailInfo(userId)
                .subscribe(result -> {
                            if (!result.isResult()) {
                                errorMessage.setValue(result.getMessage());
                                return;
                            }
                            successData.setValue(result);
                        }
                        , throwable -> {
                            errorMessage.setValue(ErrorParser.parse(throwable));
                        });
    }

}
