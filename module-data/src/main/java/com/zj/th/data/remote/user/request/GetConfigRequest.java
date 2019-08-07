package com.zj.th.data.remote.user.request;

import java.io.Serializable;

/**
 * <p>
 * {
 * "appType": 1,
 * "deviceNo": "string"
 * <p>
 * appType (integer, optional): app类型 1-ios 2-安卓 = ['1', '2'],
 * deviceNo (string, optional): 设备唯一码
 * <p>
 * }
 */
public class GetConfigRequest implements Serializable {

    private int appType = 2;
    private String deviceNo = "";

    public GetConfigRequest() {
    }

    public GetConfigRequest(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
