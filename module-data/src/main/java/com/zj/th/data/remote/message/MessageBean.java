package com.zj.th.data.remote.message;

import java.io.Serializable;

public class MessageBean implements Serializable {


    private String id;
    private String mpId="";
    private String mpIdentify;
    private String mpContent;
    private String cpName;
    private int mpType;
    private String userName;
    private int sendStatus;
    private int receiveStatus;
    private String mpSendTime;
    private String skipUrl;
    private String mpTitle;
    private int type = 1;//0:时间 1：消息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getMpContent() {
        return mpContent;
    }

    public void setMpContent(String mpContent) {
        this.mpContent = mpContent;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getMpType() {
        return mpType;
    }

    public void setMpType(int mpType) {
        this.mpType = mpType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(int receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getMpSendTime() {
        return mpSendTime;
    }

    public void setMpSendTime(String mpSendTime) {
        this.mpSendTime = mpSendTime;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getMpTitle() {
        return mpTitle;
    }

    public void setMpTitle(String mpTitle) {
        this.mpTitle = mpTitle;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMpIdentify() {
        return mpIdentify;
    }

    public void setMpIdentify(String mpIdentify) {
        this.mpIdentify = mpIdentify;
    }
}
