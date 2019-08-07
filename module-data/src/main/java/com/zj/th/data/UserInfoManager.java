package com.zj.th.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zj.th.data.local.pojo.UserInfo;
import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.user.GetConfigsResponse;
import com.zj.th.data.remote.user.LoginUserDTO;
import com.zj.th.data.remote.user.UserDetailInfo;
import com.zj.th.data.remote.user.request.GetConfigRequest;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class UserInfoManager {

    private static UserInfoManager mInstance;

    private final MutableLiveData<UserInfo> userInfoLiveData = new MutableLiveData<>();

    /**
     * UserInfoManager拼接了getPermission和getUserDetailInfo两个接口的数据
     * 下面两个字段存储了接口源数据
     */
    private LoginUserDTO originPermission;
    private UserDetailInfo originUserDetailInfo;
    private GetConfigsResponse originConfigsResponse;

    public UserInfoManager() {

    }

    public static UserInfoManager get() {
        if (mInstance == null) {
            mInstance = new UserInfoManager();
        }
        return mInstance;
    }

    public LiveData<UserInfo> getLiveUserInfo() {
        return userInfoLiveData;
    }

    public UserInfo getUserInfo() {
        return userInfoLiveData.getValue();
    }

    public void updateUserInfo() {
        loadUserInfo().subscribe(result -> {
        }, throwable -> throwable.printStackTrace());
    }

    public Observable<UserInfo> loadUserInfo() {
        return Observable.create(((ObservableOnSubscribe<UserInfo>) emitter -> {

            UserInfo info = new UserInfo();

            ThApi.getBaseService()
                    .getPermission()
                    .subscribeOn(Schedulers.io())
                    .doOnNext(result -> result.assertSuccess())
                    .doOnNext(result -> result.assertDataNonNull())
                    .doOnNext(result -> result.getData().assertPositionNonNull())
                    .flatMap(result -> {

                        originPermission = result.getData();

                        LoginUserDTO loginUser = result.getData();

                        info.setName(loginUser.getUserName());
                        info.setGlobalUserId(loginUser.getUserId());
                        info.setTelephone(loginUser.getTelephone());
                        return ThApi.getAuthService()
                                .getConfigs(new GetConfigRequest(ThApi.deviceId));
                    })
                    .doOnNext(result -> result.assertSuccess())
                    .doOnNext(result -> result.assertDataNonNull())
                    .flatMap(result -> {

                        originConfigsResponse = result.getData();
                        /**
                         * 新增
                         * */
                        info.setMaxUploadDistance(result.getData().getMaxUploadDistance());
                        info.setValidateCodeType(result.getData().getValidateCodeType());

                        return ThApi.getAuthService()
                                .getUserDetailInfo(info.getGlobalUserId());
                    })
                    .subscribeOn(Schedulers.io())
                    .doOnNext(result -> result.assertSuccess())
                    .doOnNext(result -> result.assertDataNonNull())
                    .subscribe(result -> {

                                originUserDetailInfo = result.getData();
                                UserDetailInfo detailInfo = result.getData();
                                info.setGlobalUserId(detailInfo.getGlobalUserId());
                                emitter.onNext(info);
                            }, throwable -> {
                                try {
                                    emitter.onError(throwable);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            },
                            () -> emitter.onComplete());

        })).doOnNext(info -> userInfoLiveData.postValue(info));
    }

    public LoginUserDTO getOriginPermission() {
        return originPermission;
    }

    public UserDetailInfo getOriginUserDetailInfo() {
        return originUserDetailInfo;
    }

    public GetConfigsResponse getOriginConfigsResponse() {
        return originConfigsResponse;
    }

}
