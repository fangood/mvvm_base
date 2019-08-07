package com.zj.th.data.remote.user;

import java.io.Serializable;


public class GetConfigsResponse implements Serializable {


    public PersonalVerifyConfig personalVerifyConfig;
    private String downloadORCodePath;

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

    public String getDownloadORCodePath() {
        return downloadORCodePath;
    }

    public PersonalVerifyConfig getPersonalVerifyConfig() {
        return personalVerifyConfig;
    }

    public void setPersonalVerifyConfig(PersonalVerifyConfig personalVerifyConfig) {
        this.personalVerifyConfig = personalVerifyConfig;
    }

    public static class PersonalVerifyConfig implements Serializable {

        /**
         * 是否验证身份
         * 0 关闭
         * 1 开启
         */
        private int verifyType;
        private double verifyScore;
        private String appId;
        private String appKey;

        public int getVerifyType() {
            return verifyType;
        }

        public void setVerifyType(int verifyType) {
            this.verifyType = verifyType;
        }

        public double getVerifyScore() {
            return verifyScore;
        }

        public void setVerifyScore(double verifyScore) {
            this.verifyScore = verifyScore;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }
    }
}
