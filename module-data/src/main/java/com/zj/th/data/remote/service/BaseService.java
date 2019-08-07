package com.zj.th.data.remote.service;

import com.zj.th.data.remote.BuildConfig;
import com.zj.th.data.remote.contacts.FriendBean;
import com.zj.th.data.remote.message.MessageBean;
import com.zj.th.data.remote.order.City;
import com.zj.th.data.remote.request.CheckPhoneRequest;
import com.zj.th.data.remote.request.CheckVersionRequest;
import com.zj.th.data.remote.request.LoginByPasswordRequest;
import com.zj.th.data.remote.request.LoginBySmsCodeRequest;
import com.zj.th.data.remote.request.RegisterRequest;
import com.zj.th.data.remote.response.ApiJavaResult;
import com.zj.th.data.remote.response.ApiResult;
import com.zj.th.data.remote.response.CheckVersionResponse;
import com.zj.th.data.remote.user.CheckSmsCode;
import com.zj.th.data.remote.user.CheckSmsCodeAndPassword;
import com.zj.th.data.remote.user.LoginUserDTO;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface BaseService {

    String BASE_URL = "Base/api/";
    String PATH_APP_CONFIG = BASE_URL + "AppConfig/";

    @POST(PATH_APP_CONFIG + "CheckVersion")
    Observable<ApiResult<CheckVersionResponse>> checkVersion(@Body CheckVersionRequest body);

    String PATH_BASIC_INFO = BASE_URL + "BasicInfo/";

    @GET(PATH_BASIC_INFO + "GetCity")
    Observable<ApiResult<List<City>>> getCity();
    //endregion Customer

    //region Friend

    String PATH_FRIEND = BASE_URL + "Friend/";

    /**
     * 获取好友列表
     *
     * @param globalUserId
     * @return
     */
    @GET(PATH_FRIEND + "GetFriendList")
    Observable<ApiResult<HashMap<String, List<FriendBean>>>> getFriendList(@Query("GlobalUserId") int globalUserId);

    String PATH_USER = BASE_URL + "User/";

    /**
     * 验证手机号是否存在   2018-07-19
     */
    @POST(PATH_USER + "RegisterVerify")
    Observable<ApiResult<String>> RegisterVerify(@Body CheckPhoneRequest request);


    /**
     * 获取用户权限信息
     *
     * @return
     */
    @GET(PATH_USER + "GetPermissionsByApp")
    Observable<ApiResult<LoginUserDTO>> getPermission();

    /**
     * 发送验证码
     *
     * @param phone
     * @param type
     * @return
     */
    @POST(PATH_USER + "PostCode")
    Observable<ApiResult<String>> postCode(@Query("customerAccount") String phone, @Query("mSMSCode") int type);

    /**
     * 校验验证码
     *
     * @param body
     * @return
     */
    @POST(PATH_USER + "checkSmsCode")
    Observable<ApiResult> checkSmsCode(@Body CheckSmsCode body);

    /**
     * 校验验证码和密码
     *
     * @param body
     * @return
     */
    @POST(PATH_USER + "checkSmsCodeAndPassword")
    Observable<ApiResult> checkSmsCodeAndPassword(@Body CheckSmsCodeAndPassword body);

    /**
     * 注册接口
     *
     * @param body
     * @return
     */
    @POST(PATH_USER + "Register")
    Observable<ApiResult> register(@Body RegisterRequest body);

    /**
     * 手机号密码登录
     *
     * @param body
     * @return
     */
    @POST("identityServer/account/loginByUserName")
    Observable<ApiResult<String>> loginByName(@Body LoginByPasswordRequest body);

    /**
     * 验证码登录
     *
     * @param body
     * @return
     */
    @POST("identityServer/account/loginBySmsCode")
    Observable<ApiResult<String>> loginBySmsCode(@Body LoginBySmsCodeRequest body);

    /**
     * 退出登陆
     *
     * @return
     */
    @POST("identityServer/account/logout")
    Observable<ApiResult> logout();


    /**
     * 获取消息平台token
     *
     * @param map
     * @return
     */
    @POST(BuildConfig.APP_PUSH_URL + "/security/getToken")
    Observable<ApiJavaResult<String>> getPushToken(@Body HashMap<String, String> map);

    /**
     * 查询消息
     *
     * @param map
     * @return
     */
    @POST(BuildConfig.APP_PUSH_URL + "/message/queryUserMessageList")
    Observable<ApiJavaResult<List<MessageBean>>> queryMessages(@Body HashMap<String, String> map);

    /**
     * 同步已读消息
     *
     * @param map
     * @return
     */
    @POST(BuildConfig.APP_PUSH_URL + "/message/updateMessageUserRange")
    Observable<ApiJavaResult<String>> updateReadMsg(@Body HashMap<String, String> map);


}
