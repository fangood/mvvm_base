package com.zj.th.user.message;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zj.databinding.mvvm.viewmodel.SingleLiveEvent;
import com.zj.th.base.viewmodel.BaseViewModel;
import com.zj.th.data.remote.message.MessageBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class MessageViewModel extends BaseViewModel {

    public MutableLiveData<List<MessageBean>> messageListBean = new MutableLiveData<>();
    private SingleLiveEvent<String> message = new SingleLiveEvent<>();
    protected SingleLiveEvent refreshComplete = new SingleLiveEvent<>();
    protected SingleLiveEvent loadMoreComplete = new SingleLiveEvent<>();

    protected int currentPage = 1;
    protected int mNextPageIndex;
    protected int mTotalPages = Integer.MAX_VALUE;
    protected boolean hasLoadingPage = false;


    public void refresh() {
        if (!hasLoadingPage) {
            currentPage = 1;
            mNextPageIndex = 1;
            queryMessageList(1);
        }
    }

    public void loadMore() {
        if (!hasLoadingPage) {
            if (mNextPageIndex <= mTotalPages) {
                queryMessageList(mNextPageIndex);
            } else {
                onQueryOrderListFinish();
                message.setValue("没有更多消息了");
            }
        }
    }

    public void onQueryMessageListFail(Throwable throwable) {
        Log.e("MessageViewModel", "onQueryMessageListFail: " + throwable);
    }

    @SuppressLint("CheckResult")
    public void queryMessageList(int pageIndex) {
        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("token", ACache.get(App.get().getApplicationContext()).getAsString("jpushtoken"));
//        hashMap.put("userId", App.get().getLoginUser().getUserId() + "");
        hashMap.put("appChannelId", "13f04f5c-3d6d-11e8-b0c7-a0481c7d4a7c");
        hashMap.put("pageSize", 20 + "");
        hashMap.put("currentPage", pageIndex + "");
        new MessageModel().getMessages(hashMap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> messageListBean.setValue(result.getData()),
                        throwable -> onQueryMessageListFail(throwable));
    }

    protected void onQueryOrderListFinish() {
        hasLoadingPage = false;
        if (mNextPageIndex == currentPage) {
            refreshComplete.call();
        } else {
            loadMoreComplete.call();
        }
    }

    public LiveData getRefreshComplete() {
        return refreshComplete;
    }

    public LiveData getLoadMoreComplete() {
        return loadMoreComplete;
    }

    public LiveData getMessage() {
        return message;
    }
}
