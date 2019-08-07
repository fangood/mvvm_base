package com.zj.th.data.remote.user;

import android.text.TextUtils;

import java.io.Serializable;


public class LzbPositionDto implements Serializable {
    private String positionNo;
    private String positionName;
    private int orderTypes;
    /**
     * 是否需要身份认证
     */
    private boolean isNeedIdentityAuth;

    /**
     * 所在岗位是否需要生物识别
     */
    private boolean isNeedBiometrics;
    /**
     * 所在岗位是否需要资质审核
     */
    private boolean isAuditFlag;
    /**
     * 补料配置
     */
    private boolean isMaterialFlag;


    /**
     * 20180830  两周半3.1.1新增  是否需要文字验证码
     */
    private boolean decorationOrderNeedValidateCode;
    /**
     * 20180830  两周半3.1.1新增  是否需要人脸识别
     */
    private boolean decorationOrderNeedBiomotrisc;

    private boolean isNeedVideoAuth;
    private boolean isNeedPolicyAuth;

    public boolean isDecorationOrderNeedValidateCode() {
        return decorationOrderNeedValidateCode;
    }

    public void setDecorationOrderNeedValidateCode(boolean decorationOrderNeedValidateCode) {
        this.decorationOrderNeedValidateCode = decorationOrderNeedValidateCode;
    }

    public boolean isDecorationOrderNeedBiomotrisc() {
        return decorationOrderNeedBiomotrisc;
    }

    public void setDecorationOrderNeedBiomotrisc(boolean decorationOrderNeedBiomotrisc) {
        this.decorationOrderNeedBiomotrisc = decorationOrderNeedBiomotrisc;
    }


    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(int orderTypes) {
        this.orderTypes = orderTypes;
    }

    public boolean isNeedIdentityAuth() {
        return isNeedIdentityAuth;
    }

    public void setNeedIdentityAuth(boolean needIdentityAuth) {
        isNeedIdentityAuth = needIdentityAuth;
    }

    public boolean isNeedBiometrics() {
        return isNeedBiometrics;
    }

    public void setNeedBiometrics(boolean needBiometrics) {
        isNeedBiometrics = needBiometrics;
    }

    public boolean isAuditFlag() {
        return isAuditFlag;
    }

    public void setAuditFlag(boolean auditFlag) {
        isAuditFlag = auditFlag;
    }

    public boolean isMaterialFlag() {
        return isMaterialFlag;
    }

    public void setMaterialFlag(boolean materialFlag) {
        isMaterialFlag = materialFlag;
    }

    public boolean isNeedVideoAuth() {
        return isNeedVideoAuth;
    }

    public void setNeedVideoAuth(boolean needVideoAuth) {
        isNeedVideoAuth = needVideoAuth;
    }

    public boolean isNeedPolicyAuth() {
        return isNeedPolicyAuth;
    }

    public void setNeedPolicyAuth(boolean needPolicyAuth) {
        isNeedPolicyAuth = needPolicyAuth;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof LzbPositionDto) {
            return TextUtils.equals(positionNo, ((LzbPositionDto) obj).getPositionNo());
        }
        return false;
    }
}
