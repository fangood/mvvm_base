package com.zj.th.data.remote.user;

import java.io.Serializable;

public class CheckSmsCodeAndPassword implements Serializable {

    private String mobile;
    private String smsCode;
    private String password;
    private int type;

    public CheckSmsCodeAndPassword(String mobile, String smsCode, String password, int type) {
        this.mobile = mobile;
        this.smsCode = smsCode;
        this.password = password;
        this.type = type;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
