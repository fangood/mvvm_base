package com.zj.th.data.remote.request;

import java.io.Serializable;

public class CheckVersionRequest implements Serializable {

    private String version;
    private int appType = 2;

    public CheckVersionRequest(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }
}
