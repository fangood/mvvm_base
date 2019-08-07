package com.zj.th.data.remote.user;

import java.io.Serializable;

public class UserDetailInfo implements Serializable {

    private int globalUserId;
    private String customerName;
    private String telephone;
    private String position;
    private String avatarSrc;
    /**
     * 身份证
     */
    private String nric;
    private String sex;
    private String city;
    private String district;
    private String email;
    /**
     * 等级名称
     */
    private String levelName;
    /**
     * isAuditFlag 是否需要资质认证
     */
    private boolean isAuditFlag;
    /**
     * 是否项目经理 （项目经理显示 工资明细）
     */
    private boolean isProjectManager;
    /**
     * 身份认证是否通过
     * true 已认证，
     * false 未认证
     */
    private boolean isIdentityAuth;
    /**
     * 是否显示工作计划
     */
    private boolean isPlan;
    /**
     * 是否显示我的等级
     */
    private boolean isPointDisplay; // 等级
    /**
     * 身份认证是否 显示 true 显示
     */
    private boolean isNeedIdentityAuth;

    public boolean isLogisticsUnlockDisplay() {
        return isLogisticsUnlockDisplay;
    }

    public void setLogisticsUnlockDisplay(boolean logisticsUnlockDisplay) {
        isLogisticsUnlockDisplay = logisticsUnlockDisplay;
    }

    /**是否是展示物流开锁*/
    private boolean isLogisticsUnlockDisplay;
    private String reason;
    private String freezeET;
    private String freezeST;
    /**
     * 视频认证内容
     */
    private String videoMsg;
    /**
     * 视频时长（秒)
     */
    private int viedoLimit;
    /**
     * 资质认证是否有更新标记字段
     */
    private boolean credientAuthUpdateFlag;
    /**
     * 视频认证状态
     */
    private int videoAuthStatus;
    /**
     * 保单认证状态
     */
    private int policyAuthStatus;
    /**
     * 视频大小/M
     */
    private int videoSize;
    /**
     * 单位M
     */
    private int policyFileSize;
    private int policyFileCount;

    public int getGlobalUserId() {
        return globalUserId;
    }

    public void setGlobalUserId(int globalUserId) {
        this.globalUserId = globalUserId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAvatarSrc() {
        return avatarSrc;
    }

    public void setAvatarSrc(String avatarSrc) {
        this.avatarSrc = avatarSrc;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public boolean isAuditFlag() {
        return isAuditFlag;
    }

    public void setAuditFlag(boolean auditFlag) {
        isAuditFlag = auditFlag;
    }

    public boolean isProjectManager() {
        return isProjectManager;
    }

    public void setProjectManager(boolean projectManager) {
        isProjectManager = projectManager;
    }

    public boolean isIdentityAuth() {
        return isIdentityAuth;
    }

    public void setIdentityAuth(boolean identityAuth) {
        isIdentityAuth = identityAuth;
    }

    public boolean isPlan() {
        return isPlan;
    }

    public void setPlan(boolean plan) {
        isPlan = plan;
    }

    public boolean isPointDisplay() {
        return isPointDisplay;
    }

    public void setPointDisplay(boolean pointDisplay) {
        isPointDisplay = pointDisplay;
    }

    public boolean isNeedIdentityAuth() {
        return isNeedIdentityAuth;
    }

    public void setNeedIdentityAuth(boolean needIdentityAuth) {
        isNeedIdentityAuth = needIdentityAuth;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFreezeET() {
        return freezeET;
    }

    public void setFreezeET(String freezeET) {
        this.freezeET = freezeET;
    }

    public String getFreezeST() {
        return freezeST;
    }

    public void setFreezeST(String freezeST) {
        this.freezeST = freezeST;
    }

    public String getVideoMsg() {
        return videoMsg;
    }

    public void setVideoMsg(String videoMsg) {
        this.videoMsg = videoMsg;
    }

    public int getViedoLimit() {
        return viedoLimit;
    }

    public void setViedoLimit(int viedoLimit) {
        this.viedoLimit = viedoLimit;
    }

    public boolean isCredientAuthUpdateFlag() {
        return credientAuthUpdateFlag;
    }

    public void setCredientAuthUpdateFlag(boolean credientAuthUpdateFlag) {
        this.credientAuthUpdateFlag = credientAuthUpdateFlag;
    }

    public int getVideoAuthStatus() {
        return videoAuthStatus;
    }

    public void setVideoAuthStatus(int videoAuthStatus) {
        this.videoAuthStatus = videoAuthStatus;
    }

    public int getPolicyAuthStatus() {
        return policyAuthStatus;
    }

    public void setPolicyAuthStatus(int policyAuthStatus) {
        this.policyAuthStatus = policyAuthStatus;
    }

    public int getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(int videoSize) {
        this.videoSize = videoSize;
    }

    public int getPolicyFileSize() {
        return policyFileSize;
    }

    public void setPolicyFileSize(int policyFileSize) {
        this.policyFileSize = policyFileSize;
    }

    public int getPolicyFileCount() {
        return policyFileCount;
    }

    public void setPolicyFileCount(int policyFileCount) {
        this.policyFileCount = policyFileCount;
    }


}
