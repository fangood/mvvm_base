package com.zj.th.data.remote.request;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class PostCodeRequest extends Request {

    @IntDef({SMSCODE_TYPE_REGISTER,
            SMSCODE_TYPE_LOGIN,
            SMSCODE_TYPE_CHANGE_PHONE,
            SMSCODE_TYPE_RESET_PASSWORD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SmsCodeType {
    }

    /**
     * 注册
     */
    public static final int SMSCODE_TYPE_REGISTER = 1;
    /**
     * 登录
     */
    public static final int SMSCODE_TYPE_LOGIN = 2;
    /**
     * 登录
     */
    public static final int SMSCODE_TYPE_CHANGE_PHONE = 4;
    /**
     * 找回密码
     */
    public static final int SMSCODE_TYPE_RESET_PASSWORD = 6;


    private String customerAccount;
    private int mSMSCode;

    public PostCodeRequest(String phone, @SmsCodeType int type) {
        this.customerAccount = phone;
        this.mSMSCode = type;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String phone) {
        this.customerAccount = phone;
    }

    public int getmSMSCode() {
        return mSMSCode;
    }

    public void setmSMSCode(@SmsCodeType int type) {
        this.mSMSCode = type;
    }
}
