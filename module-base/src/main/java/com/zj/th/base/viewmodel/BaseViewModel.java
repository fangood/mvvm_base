package com.zj.th.base.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zj.th.data.remote.response.ApiResult;

public class BaseViewModel extends ViewModel {


    public MutableLiveData<String> errorMessage = new MutableLiveData<>();
    //需要数据
    public MutableLiveData<ApiResult> successData = new MutableLiveData<>();
    // 不需要数据 只需要响应
    public MutableLiveData<String> successResponse = new MutableLiveData<>();

}
