package com.zj.th.data.remote.user;

import java.io.Serializable;



public class CheckSmsCode implements Serializable {

    private String mobile;
    private String smsCode;
    private int type;

    public CheckSmsCode(String mobile, String smsCode, int type) {
        this.mobile = mobile;
        this.smsCode = smsCode;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
