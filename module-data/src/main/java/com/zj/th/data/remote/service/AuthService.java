package com.zj.th.data.remote.service;

import com.zj.th.data.remote.response.ApiResult;
import com.zj.th.data.remote.user.GetConfigsResponse;
import com.zj.th.data.remote.user.UserDetailInfo;
import com.zj.th.data.remote.user.request.GetConfigRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface AuthService {

    String PATH_BASE = "Base";

    @FormUrlEncoded
    @POST("Account/Login")
    Observable<ApiResult<String>> login(@Field("CustomerAccount") String phone, @Field("Password") String password);

//    user
    @GET(PATH_BASE + "/api/Customer/GetCustomerInfo")
    Observable<ApiResult<UserDetailInfo>> getUserDetailInfo(@Query("GlobalUserId") int UserId);

    //配置接口
    @POST(PATH_BASE + "/api/AppConfig/GetConfigs")
    Observable<ApiResult<GetConfigsResponse>> getConfigs(
            @Body GetConfigRequest request);



}
