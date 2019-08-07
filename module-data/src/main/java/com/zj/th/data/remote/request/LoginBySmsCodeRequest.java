package com.zj.th.data.remote.request;

public class LoginBySmsCodeRequest extends Request {

    private String mobile;
    private String smsCode;

    public LoginBySmsCodeRequest(String mobile, String smsCode) {
        this.mobile = mobile;
        this.smsCode = smsCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
