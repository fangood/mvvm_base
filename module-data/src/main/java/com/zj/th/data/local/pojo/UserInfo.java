package com.zj.th.data.local.pojo;

import com.zj.th.data.remote.user.GetConfigsResponse;
import com.zj.th.data.remote.user.LzbPersonFunctionDto;
import com.zj.th.data.remote.user.LzbPositionDto;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 身份证
     */
    private String nric;

    /**
     * 性别
     */
    private String sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String district;

    /**
     * 是否需要资质认证
     */
    private boolean isAuditFlag;

    /**
     * 是否项目经理 （项目经理显示 工资明细）
     */
    private boolean isProjectManager;

    /**
     * 是否显示工作计划
     */
    private boolean isPlan;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 等级名称
     */
    private String levelName;

    /**
     * 是否显示我的等级
     */
    private boolean isPointDisplay;

    /**
     * 是否已经通过身份认证
     */
    private boolean isIdentityAuth;

    /**
     * 是否需要显示身份
     */
    private boolean isNeedIdentityAuth;

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

    /**
     * 用户标识
     */
    private int globalUserId;

    /**
     * 权限组信息，用,号分割的
     */
    private String groupInfo;

    /**
     * 主岗位,员工没有主岗位
     */
    private LzbPositionDto position;

    /**
     * 副岗 ,
     */
    private List<LzbPositionDto> subPosition;

    /**
     * 个人权限
     */
    private List<LzbPersonFunctionDto> personFunctionJsonDtoList;

    private GetConfigsResponse.PersonalVerifyConfig personalVerifyConfig;

    private String downloadORCodePath;


    /**
     * 新加的字段
     * */
    float maxUploadDistance;
    int validateCodeType;

    public float getMaxUploadDistance() {
        return maxUploadDistance;
    }

    public void setMaxUploadDistance(float maxUploadDistance) {
        this.maxUploadDistance = maxUploadDistance;
    }

    public int getValidateCodeType() {
        return validateCodeType;
    }

    public void setValidateCodeType(int validateCodeType) {
        this.validateCodeType = validateCodeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public boolean isPlan() {
        return isPlan;
    }

    public void setPlan(boolean plan) {
        isPlan = plan;
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

    public boolean isPointDisplay() {
        return isPointDisplay;
    }

    public void setPointDisplay(boolean pointDisplay) {
        isPointDisplay = pointDisplay;
    }

    public boolean isIdentityAuth() {
        return isIdentityAuth;
    }

    public void setIdentityAuth(boolean identityAuth) {
        isIdentityAuth = identityAuth;
    }

    public boolean isNeedIdentityAuth() {
        return isNeedIdentityAuth;
    }

    public void setNeedIdentityAuth(boolean needIdentityAuth) {
        isNeedIdentityAuth = needIdentityAuth;
    }

    public int getGlobalUserId() {
        return globalUserId;
    }

    public void setGlobalUserId(int globalUserId) {
        this.globalUserId = globalUserId;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    public LzbPositionDto getPosition() {
        return position;
    }

    public void setPosition(LzbPositionDto position) {
        this.position = position;
    }

    public List<LzbPositionDto> getSubPosition() {
        return subPosition;
    }

    public void setSubPosition(List<LzbPositionDto> subPosition) {
        this.subPosition = subPosition;
    }

    public List<LzbPersonFunctionDto> getPersonFunctionJsonDtoList() {
        return personFunctionJsonDtoList;
    }

    public void setPersonFunctionJsonDtoList(List<LzbPersonFunctionDto> personFunctionJsonDtoList) {
        this.personFunctionJsonDtoList = personFunctionJsonDtoList;
    }

    public GetConfigsResponse.PersonalVerifyConfig getPersonalVerifyConfig() {
        return personalVerifyConfig;
    }

    public void setPersonalVerifyConfig(GetConfigsResponse.PersonalVerifyConfig personalVerifyConfig) {
        this.personalVerifyConfig = personalVerifyConfig;
    }

    public String getDownloadORCodePath() {
        return downloadORCodePath;
    }

    public void setDownloadORCodePath(String downloadORCodePath) {
        this.downloadORCodePath = downloadORCodePath;
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
