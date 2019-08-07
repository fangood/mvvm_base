package com.zj.th.contacts;

import com.zj.th.data.remote.ThApi;
import com.zj.th.data.remote.contacts.FriendBean;
import com.zj.th.data.remote.response.ApiResult;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;



public class ContactsModel {

    public Observable<ApiResult<HashMap<String, List<FriendBean>>>> getFriendsList() {
        return ThApi.getBaseService()
//                .getFriendList(App.get().getLoginUser().getUserId())
                .getFriendList(1235203)
                .subscribeOn(Schedulers.io())
                .doOnNext(result -> result.assertSuccess());
    }

}
